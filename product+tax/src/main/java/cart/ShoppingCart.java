package cart;

import domain.model.DigitalProduct;
import domain.model.PhysicalProduct;
import service.ProductManager;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 *
 */
public class ShoppingCart implements Comparable<ShoppingCart> {
  
  private static final ProductManager MANAGER = new ProductManager();
  private static final double BASE_FEE = 0.1;
  
  private final String id = UUID.randomUUID().toString();
  private final Set<String> products = new CopyOnWriteArraySet<>();
  private double tax = 0D;
  private double fee = 0D;
  
  public boolean addItem(String item) {
    if(products.contains(item) || !MANAGER.available(item)) {
      return false;
    }
    
    products.add(item);
    //TODO: may need to calculate tax??
    MANAGER.decrease(item, 1);
    return true;
  }
  
  public boolean removeItem(String item) {
    if(!products.contains(item)) {
      return false;
    }
    
    products.remove(item);
    tax -= Math.round(MANAGER.getByName(item).getTax().getPercentage() * MANAGER.getByName(item).getPrice() * 100) / 100D;
    MANAGER.increase(item, 1);
    return true;
  }
  
  public double cartAmount() {
    return products.stream().map(MANAGER::getByName).mapToDouble(p -> {
      double price = p.getPrice();
      //TODO: price = price * so luong item
      //TODO: apply coupon, too
      
      double tax = Math.round(price * (p.getTax().getPercentage() / 100) * 100) / 100D;
      this.tax += tax;
      if (p instanceof DigitalProduct) {
        //TODO: please apply coupon
        return price + tax;
      }
      double fee = Math.round(((PhysicalProduct) p).getWeight() * BASE_FEE * 100) / 100D;
      //TODO: please apply coupon
      this.fee += fee;
      return price + fee + tax;
    }).sum();
  }
  
  public void clear() {
    products.forEach(this::removeItem);
  }
  
  @Override
  public int compareTo(ShoppingCart o) {
    int rs = Double.compare(weight(), o.weight());
    if(rs == 0) rs = id.compareTo(o.id);
    return rs;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ShoppingCart cart = (ShoppingCart) o;
    return id.equals(cart.id);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
  
  @Override
  public String toString() {
    return "Weight: %f, amount: %f".formatted(weight(), cartAmount());
  }
  
  private double weight() {
    return products.stream().map(MANAGER::getByName).filter(PhysicalProduct.class::isInstance)
        .map(PhysicalProduct.class::cast).mapToDouble(PhysicalProduct::getWeight).sum();
  }
  
  public double getTax() {
    return tax;
  }
  
  public double getFee() {
    return fee;
  }
}
