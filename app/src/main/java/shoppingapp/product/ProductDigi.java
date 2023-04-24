package shoppingapp.product;
/**
 * @author <Ngo Chi Binh - s3938145>
 */ 
public class ProductDigi extends Product{

    private String name;

    public ProductDigi() {}

    public ProductDigi(String name, String desc, int quantity, double price) {
        super(name, desc, quantity, price);
        this.name = "DIGITAL - " + name;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return super.weight;
    }

    @Override
    public String toString() {
        return 
        "\nName: " + name 
        + "\nDescription: " + desc 
        + "\nQuantity: " + quantity 
        + "\nPrice: " + price 
        + "\n";
    }

}
