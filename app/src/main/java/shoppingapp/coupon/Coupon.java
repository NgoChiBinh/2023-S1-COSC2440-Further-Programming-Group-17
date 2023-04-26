package shoppingapp.coupon;

import java.util.*;
import shoppingapp.product.Product;

public class Coupon {
    
    private Product discountProduct;
    
    private String couponCode;

    private int discountPercent;

    private double discountPrice;

    public Coupon() {};

    public Coupon(Product discountProduct) {
        this.discountProduct = discountProduct;
    }

    public Product getDiscountProduct() { return discountProduct; }

    public String getCouponCode() { return couponCode; }

    public int getDiscountPercentage() { return discountPercent; }

    public double getDiscountPrice() { return discountPrice; }

    @Override
    public String toString() {
        return "Coupon: " + 
                "\n" + "\t\tDiscount applies to: " + discountProduct + 
                "\n" + "\t\tCoupon Code: " + couponCode + 
                "\n";
    }

    public class CouponCatalog {
        
        private static Set <Coupon>couponCatalog = new LinkedHashSet<Coupon>();

        private static Iterator<Coupon> iterator;

        public CouponCatalog() {};

        public static boolean addCouponToCatalog (Coupon coupon) {
            return couponCatalog.add(coupon);
        }

        public static void displayCouponsCatalog() {
            for (Coupon coupon : couponCatalog) {
                System.out.println(coupon);
            }
        }

        public static Coupon findCoupon (String productName) {
            iterator = couponCatalog.iterator();

            while(iterator.hasNext()) {
                Coupon coupon = iterator.next();
                if (coupon.getDiscountProduct().getName().contains(productName)) {
                    return coupon;
                }
            }
            return null;
        }

        public static Coupon findCouponBasedOnCode (String couponCode)
        {
            iterator = couponCatalog.iterator();

            while(iterator.hasNext()) {
                Coupon coupon = iterator.next();
                if (coupon.getCouponCode().contains(couponCode)) {
                    return coupon;
                }
            }
            return null;
        }

        public static Set<Coupon> findCoupons (String productName)
        {
            Set<Coupon> productCoupons = new  LinkedHashSet<Coupon>();
            iterator = couponCatalog.iterator();

            while(iterator.hasNext()) {
                Coupon coupon = iterator.next();
                if (coupon.getDiscountProduct().getName().contains(productName)) {
                    productCoupons.add(coupon);
                }
            }
            return productCoupons;
        }
    }

}
