package domain.model;

/**
 *
 */
public class DigitalProduct extends Product {
  
  public DigitalProduct(String name, String description, int quantity, double price, Product.Tax tax) {
    super(Type.DIGITAL, name, description, quantity, price, tax);
  }
  
}
