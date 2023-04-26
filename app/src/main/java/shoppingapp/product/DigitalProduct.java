package shoppingapp.product;
/**
 * @author <Ngo Chi Binh - s3938145>
 */
public class DigitalProduct extends Product{
    public DigitalProduct(String name, String description, int quantity, double price, boolean isGift) {
        super(name, description, quantity, price, isGift);
    }

    @Override
    public String getType(){
        return ProductHelpers.TYPE.DIGITAL.toString();
    }
}
