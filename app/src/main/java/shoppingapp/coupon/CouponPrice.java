package shoppingapp.coupon;

import shoppingapp.product.Product;

public class CouponPrice extends Coupon{

    //     public static void main(String[] args) {
    //     ProductDigi digital_1 = new ProductDigi("Malwarebytes", "Software", 100, 200);
    //     CouponPrice coupon_1 = new CouponPrice(digital_1, 20);

    //     ProductPhys physical_1 = new ProductPhys("HP Envy", "Laptop", 3, 200, 5);
    //     CouponPrice coupon_2 = new CouponPrice(physical_1, 40.1234);

    //     System.out.println(coupon_1.getCouponCode());
    //     System.out.println(coupon_2.getCouponCode());
    // }

    private double discountPrice;

    private String couponCode;

    public CouponPrice() {};

    public CouponPrice(Product discounProduct, double discountPrice) {
        super(discounProduct);
        this.discountPrice = discountPrice;
        this.couponCode = genCouponCode();
    }

    public double getDiscountPrice() { return discountPrice; }

    public String getCouponCode() { return this.couponCode;}

    public String genCouponCode() {
        StringBuilder percentCode = new StringBuilder();

        String couponCode =  CouponCodeGen.genCode(getDiscountProduct());

        percentCode.append(couponCode + "OFF" + String.format("%.2f" ,this.discountPrice) + "$");

        return percentCode.toString();
    }

    @Override
    public String toString() {
        return "Fixed Price Discount Coupon " +
        "\n" + "\t\tDiscount Product: " + getDiscountProduct().getName() +
        "\n" + "\t\tDiscount Amount: $" + discountPrice + 
        "\n" + "\t\tCounpon Code: " + couponCode + 
        "\n";
    }
}
