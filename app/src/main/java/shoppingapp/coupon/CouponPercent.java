package shoppingapp.coupon;

import shoppingapp.product.Product;

public class CouponPercent extends Coupon{

    // public static void main(String[] args) {
    //     ProductDigi digital_1 = new ProductDigi("Malwarebytes", "Software", 100, 200);
    //     CouponPercent coupon_1 = new CouponPercent(digital_1, 20);

    //     ProductPhys physical_1 = new ProductPhys("HP Envy", "Laptop", 3, 200, 5);
    //     CouponPercent coupon_2 = new CouponPercent(physical_1, 40);

    //     System.out.println(coupon_1.getCouponCode());
    //     System.out.println(coupon_2.getCouponCode());
    // }
    
    private int discountPercent;

    private String couponCode;

    public CouponPercent() {};

    public CouponPercent(Product discountProduct, int discountPercent) {
        super(discountProduct);
        this.discountPercent = discountPercent;
        this.couponCode = genCouponCode();
    }

    public int getDiscountPercentage() { return discountPercent;}

    public String getCouponCode() { return this.couponCode;}

    //Generate percentage discount coupon code
    public String genCouponCode() {
        StringBuilder percentCode = new StringBuilder();

        String couponCode =  CouponCodeGen.genCode(getDiscountProduct());

        percentCode.append(couponCode + "OFF" + this.discountPercent + "%");

        return percentCode.toString();
    }

    @Override
    public String toString() {
        return "Percent Discount Coupon: " + 
                "\n" + "\t\tDiscount applies to: " + getDiscountProduct().getName() + 
                "\n" + "\t\tDiscount Percentage: " + discountPercent + "%" +
                "\n" + "\t\tCoupon Code: " + couponCode + 
                "\n";
    }
}
