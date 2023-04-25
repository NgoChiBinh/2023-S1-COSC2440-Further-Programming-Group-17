package domain.model;

/**
 *
 */
public class PhysicalProduct extends Product {
  
  private double weight;
  
  public PhysicalProduct(String name, String description, int quantity, double price, double weight, Product.Tax tax) {
    super(Type.PHYSICAL, name, description, quantity, price, tax);
    ensureNonNegativeNumber(weight, "weight");
    this.weight = weight;
  }
  
  public double getWeight() {
    return weight;
  }
  
  public void setWeight(double weight) {
    ensureNonNegativeNumber(weight, "weight");
    this.weight = weight;
  }
}
