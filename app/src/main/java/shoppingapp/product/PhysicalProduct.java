package shoppingapp.product;
/**
 * @author <Ngo Chi Binh - s3938145>
 */
public class PhysicalProduct extends Product{
    protected Double weight;

    public PhysicalProduct(String name, String description, int quantity, Double price, Double weight, boolean isGift) {
        super(name, description, quantity, price, isGift);
        this.weight = weight;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public String getType() {
        return ProductHelpers.TYPE.PHYSICAL.toString();
    }
}
