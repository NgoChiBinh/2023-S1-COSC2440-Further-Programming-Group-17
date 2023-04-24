package shoppingapp.product;
/**
 * @author <Ngo Chi Binh - s3938145>
 */ 
public class ProductPhys extends Product{

    private String name;

    public ProductPhys () {}

    public ProductPhys(String name, String desc, int quantity, double price, double weight) {
        super(name, desc, quantity, price, weight);
        this.name = "PHYSICAL - " + name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return 
        "\nName: " + name 
        + "\nDescription: " + desc 
        + "\nQuantity: " + quantity 
        + "\nPrice: " + price 
        + "\nWeight: " + weight 
        + "\n";
    }

}
