package shoppingapp;
/**
 * @author <Ngo Chi Binh - s3938145>
 */ 
public class ShippingFee {
    
    private static double finalFee;

    private static double baseFee = 0.1;

    public static double calcFee(double totalCartWeight) {
        finalFee = totalCartWeight * baseFee;
        return finalFee;
    }
}
