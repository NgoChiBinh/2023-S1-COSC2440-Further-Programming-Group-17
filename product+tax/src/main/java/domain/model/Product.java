package domain.model;

import java.util.Objects;
import java.util.stream.Stream;

/**
 *
 */
public class Product {

  private String name;
  private String description;
  private int quantity;
  private double price;
  private String message;
  private Tax tax;
  
  private final Type type;
  
  protected Product(Type type, String name, String description, int quantity, double price, Tax tax) {
    Objects.requireNonNull(type, "type cannot be null");
    Objects.requireNonNull(name, "name cannot be null");
    ensureNonNegativeNumber(quantity, "quantity");
    ensureNonNegativeNumber(price, "price");
  
    this.type = type;
    this.name = name;
    this.description = description;
    this.quantity = quantity;
    this.price = price;
    this.tax = tax;
  }
  
  void ensureNonNegativeNumber(double number, String property) {
    if(number < 0) {
      throw new IllegalArgumentException("%s cannot be negative".formatted(property));
    }
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getDescription() {
    return description;
  }
  
  public void setDescription(String description) {
    this.description = description;
  }
  
  public int getQuantity() {
    return quantity;
  }
  
  public void setQuantity(int quantity) {
    ensureNonNegativeNumber(quantity, "quantity");
    this.quantity = quantity;
  }
  
  public double getPrice() {
    return price;
  }
  
  public void setPrice(double price) {
    ensureNonNegativeNumber(price, "price");
    this.price = price;
  }
  
  public Type getType() {
    return type;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Product product = (Product) o;
    return name.equals(product.name);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(name);
  }
  
  @Override
  public String toString() {
    return type.name() + " - " + name;
  }
  
  public String getMessage() {
    return message;
  }
  
  public void setMessage(String message) {
    this.message = message;
  }
  
  public Tax getTax() {
    return tax;
  }
  
  public void setTax(Tax tax) {
    this.tax = tax;
  }
  
  public enum Type {
    
    DIGITAL(1), PHYSICAL(2);
    
    private static final Type[] VALUES = Type.values();
    
    private final int code;
  
    Type(int code) {
      this.code = code;
    }
  
    public int getCode() {
      return code;
    }
    
    public static Type fromCode(int code) {
      return Stream.of(VALUES).filter(t -> t.getCode() == code).findFirst().orElse(null);
    }
  }
  
  public enum Tax {
  
    FREE(0), NORMAL(10), LUXURY(20);
    
    private final double percentage;
  
    Tax(double percentage) {
      this.percentage = percentage;
    }
  
    public double getPercentage() {
      return percentage;
    }
    
    public static Tax fromPercentage(double percentage) {
      return Stream.of(values()).filter(t -> t.percentage == percentage)
          .findFirst().orElse(FREE);
    }
  }
  
}
