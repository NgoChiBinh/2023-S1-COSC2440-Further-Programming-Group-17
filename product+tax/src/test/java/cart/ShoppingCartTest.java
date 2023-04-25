package cart;

import domain.model.DigitalProduct;
import domain.model.PhysicalProduct;
import domain.model.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.ProductManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 */
public class ShoppingCartTest {
  
  static ShoppingCart cart = new ShoppingCart();
  static ProductManager manager = new ProductManager();
  
  @AfterEach
  void teardown() {
    cart.clear();
    manager.clear();
  }
  
  @Test
  public void addItem_whenAvailable_shouldReturnTrue() {
    manager.save(new DigitalProduct("name", "desc", 1, 11, Product.Tax.fromPercentage(10)));
    Assertions.assertTrue(cart.addItem("name"));
    Assertions.assertEquals(0, manager.getByName("name").getQuantity());
    Assertions.assertEquals(Product.Tax.NORMAL, manager.getByName("name").getTax());
  }
  
  @Test
  public void addItem_whenNotAvailable_shouldReturnFalse() {
    manager.save(new DigitalProduct("name", "desc", 0, 11, Product.Tax.fromPercentage(10)));
    Assertions.assertFalse(cart.addItem("name"));
    Assertions.assertEquals(0, manager.getByName("name").getQuantity());
  }
  
  @Test
  public void removeItem_whenNoItem_shouldReturnFalse() {
    Assertions.assertFalse(cart.removeItem("name"));
  }
  
  @Test
  public void removeItem_whenHasItem_shouldReturnCorrectly() {
    manager.save(new DigitalProduct("name", "desc", 1, 11, Product.Tax.fromPercentage(10)));
    cart.addItem("name");
    Assertions.assertEquals(0, manager.getByName("name").getQuantity());
    Assertions.assertTrue(cart.removeItem("name"));
    Assertions.assertEquals(1, manager.getByName("name").getQuantity());
  }

  @Test
  public void cartAmount_whenHasItems_shouldWork() {
    manager.save(new DigitalProduct("name", "desc", 1, 11, Product.Tax.fromPercentage(10)));
    manager.save(new PhysicalProduct("name2", "desc2", 1, 22, 33, Product.Tax.fromPercentage(10)));
    manager.save(new PhysicalProduct("name3", "desc3", 1, 33, 44, Product.Tax.fromPercentage(10)));
    cart.addItem("name");
    cart.addItem("name2");
    cart.addItem("name3");
    Assertions.assertEquals(80.3, cart.cartAmount());
    Assertions.assertEquals(6.6, cart.getTax());
    Assertions.assertEquals(7.7, cart.getFee());
  }
  
  @Test
  public void cartAmount_whenNoItem_shouldWork() {
    manager.save(new DigitalProduct("name", "desc", 1, 11, Product.Tax.fromPercentage(10)));
    manager.save(new PhysicalProduct("name2", "desc2", 1, 22, 33, Product.Tax.fromPercentage(10)));
    manager.save(new PhysicalProduct("name3", "desc3", 1, 33, 44, Product.Tax.fromPercentage(10)));

    Assertions.assertEquals(0, cart.cartAmount());
  }

  @Test
  public void compareTo_shouldWork() {
    manager.save(new DigitalProduct("name", "desc", 1, 11, Product.Tax.fromPercentage(10)));
    manager.save(new PhysicalProduct("name2", "desc2", 1, 22, 33, Product.Tax.fromPercentage(10)));
    manager.save(new PhysicalProduct("name3", "desc3", 1, 33, 44, Product.Tax.fromPercentage(10)));
    
    Set<ShoppingCart> shoppingCarts = new TreeSet<>();
    ShoppingCart e = new ShoppingCart();
    e.addItem("name2");
    ShoppingCart e2 = new ShoppingCart();
    e2.addItem("name");
    shoppingCarts.add(e);
    shoppingCarts.add(e2);
  
    List<ShoppingCart> tmp = new ArrayList<>(shoppingCarts);
    Assertions.assertEquals(e2, tmp.get(0));
    Assertions.assertEquals(e, tmp.get(1));
  }

}
