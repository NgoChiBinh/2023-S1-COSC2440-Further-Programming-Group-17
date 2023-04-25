package service;

import domain.model.Product;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 *
 */
public class ProductManager {

  private static final ConcurrentMap<String, Product> PRODUCTS = new ConcurrentHashMap<>();
  
  public Product getByName(String name) {
    return PRODUCTS.get(name);
  }
  
  public void save(Product product) {
    PRODUCTS.put(product.getName(), product);
  }
  
  public boolean available(String name) {
    return contains(name) && PRODUCTS.get(name).getQuantity() > 0;
  }
  
  public void clear() {
    PRODUCTS.clear();
  }
  
  public boolean contains(String name) {
    return PRODUCTS.containsKey(name);
  }
  
  public void decrease(String name, int sub) {
    PRODUCTS.computeIfPresent(name, (k, v) -> {
      v.setQuantity(v.getQuantity() - sub);
      return v;
    });
  }
  
  public void increase(String name, int plus) {
    PRODUCTS.computeIfPresent(name, (k, v) -> {
      v.setQuantity(v.getQuantity() + plus);
      return v;
    });
  }
  
  public void remove(String name) {
    PRODUCTS.remove(name);
  }

}
