package shoppingapp.product;
import java.util.*;
/**
 * @author <Ngo Chi Binh - s3938145>
 */
import java.io.Serializable;

public abstract class Product{
    protected String name;
    protected String description;
    protected int quantity;
    protected Double price;
    protected boolean isGift;

    public Product(String name, String description, int quantity, Double price, boolean isGift){
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.isGift = isGift;
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
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isGift() {
        return isGift;
    }

    public void setGift(boolean confirmation) {
        isGift = confirmation;
    }

    @Override
    public String toString() {
        return this instanceof PhysicalProduct
                ? name + "," + description + "," + quantity + "," + price + "," + ((PhysicalProduct)this).weight + "," + isGift
                : name + "," + description + "," + quantity + "," + price + "," + isGift;
    }

    public abstract String getType();
}
