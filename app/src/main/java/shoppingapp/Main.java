package shoppingapp;

import shoppingapp.cart.CartShopping;
import shoppingapp.cart.CartsAll;
import shoppingapp.coupon.Coupon;
import shoppingapp.coupon.CouponDiscount;
import shoppingapp.coupon.CouponPercent;
import shoppingapp.coupon.CouponPrice;
import shoppingapp.menu.Menu;
import shoppingapp.product.ProductDigi;
import shoppingapp.product.ProductPhys;
import shoppingapp.product.Product.ProductCatalog;

/**
 * @author <Ngo Chi Binh - s3938145>
 */ 
public class Main {

    public static void main(String[] args) {

        Menu menu = new Menu();

        ProductDigi digital_1 = new ProductDigi("Malwarebytes", "Software", 100, 200);

        ProductDigi digital_2 = new ProductDigi("Norton", "Software", 2, 150);

        ProductPhys physical_1 = new ProductPhys("HP Envy", "Laptop", 3, 200, 5);

        ProductPhys physical_2 = new ProductPhys("Dell XP9", "Laptop", 14, 350, 6.7);

        ProductPhys physical_3 = new ProductPhys("Samsung Galazxy", "Smartphone", 3, 200, 2);

        ProductCatalog.addToCatalog(digital_1);
        ProductCatalog.addToCatalog(digital_2);
        ProductCatalog.addToCatalog(physical_1);
        ProductCatalog.addToCatalog(physical_2);
        ProductCatalog.addToCatalog(physical_3);

        // Shopping cart 1: physical + digital items
        CartShopping cart1 = new CartShopping();

        cart1.addItem(digital_1.getName());

        cart1.addItem(physical_1.getName());

        cart1.addItem(digital_2.getName());

        cart1.addItem(physical_2.getName());

        // Check out
        CartsAll.addCart(cart1);

        // Shopping cart 2: All physical items
        CartShopping cart2 = new CartShopping();

        cart2.addItem(physical_1.getName());

        cart2.addItem(physical_3.getName());

        // Check out cart 2
        CartsAll.addCart(cart2);

        // Shopping cart 3: All digital items
        CartShopping cart3 = new CartShopping();

        cart3.addItem(digital_1.getName());

        cart3.addItem(digital_2.getName());

        // Check out cart 3
        CartsAll.addCart(cart3);

        //List of all carts
        // CartsAll.allCarts();

        // Before edit
        // System.out.println(ProductCatalog.getCatalog());

        // menu.mainMenu();

        // // Cart 2 before edit
        // System.out.print(Carts.findCart(2));
        // // Edit Product
        // digital_1.setPrice(100);

        // // After edit
        // System.out.println(ProductCatalog.getCatalog());

        // cart1.cartAmount();
        // cart1.updateTotalCost();

        // System.out.print(Carts.findCart(2));

        // cart1.updateTotalCost();
        // System.out.print(Carts.findCart(2));

        // cart1.removeItem("Mal");
        // System.out.print(Carts.findCart(2));

        // digital_2.setPrice(50);
        // cart1.updateTotalCost();
        // System.out.print(Carts.findCart(2));

        //Test : only one coupon per shopping cart

        // All percent coupons
        Coupon coupon_percent_digi_1 = new CouponPercent(digital_1, 40);
        Coupon coupon_percent_digi_2 = new CouponPercent(digital_2, 30);
        Coupon coupon_percent_phys_1 = new CouponPercent(physical_1, 50);
        Coupon coupon_percent_phys_2 = new CouponPercent(physical_2, 60);
        Coupon coupon_percent_phys_3 = new CouponPercent(physical_3, 70);


        // All fixed price coupons
        Coupon coupon_price_digi_1 = new CouponPrice(digital_1, 20.99);
        Coupon coupon_price_digi_2 = new CouponPrice(digital_2, 30.59);
        Coupon coupon_price_phys_1 = new CouponPrice(physical_1, 40.44);
        Coupon coupon_price_phys_2 = new CouponPrice(physical_2, 59.99);
        Coupon coupon_price_phys_3 = new CouponPrice(physical_3, 60);

        // Testing cart ID002 and ID003
        // System.out.println(CartsAll.findCart(2));
        // System.out.println(CartsAll.findCart(3));

        // Test percent coupons
        // Apply percent coupons to cart ID002
        // CouponDiscount.addDiscountCart(cart2, coupon_percent_digi_1);
        // CouponDiscount.addDiscountCart(cart2, coupon_percent_digi_2);
        // CouponDiscount.addDiscountCart(cart2, coupon_percent_phys_1);
        // CouponDiscount.addDiscountCart(cart2, coupon_percent_phys_2);
        // CouponDiscount.addDiscountCart(cart2, coupon_percent_phys_3);

        // Apply percent coupons to cart ID003
        CouponDiscount.addDiscountCart(cart3, coupon_percent_digi_1);
       
        
        System.out.println(CouponDiscount.allDiscountedCarts());

        // // Test fixed price coupons
        // // Apply price coupons to cart ID002
        // CouponDiscount.addDiscountCart(cart2, coupon_price_digi_1);
        // CouponDiscount.addDiscountCart(cart2, coupon_price_digi_2);
        // CouponDiscount.addDiscountCart(cart2, coupon_price_phys_1);
        // CouponDiscount.addDiscountCart(cart2, coupon_price_phys_2);
        // CouponDiscount.addDiscountCart(cart2, coupon_price_phys_3);

        // // Apply price coupons to cart ID003
        // CouponDiscount.addDiscountCart(cart3, coupon_price_digi_1);
        // CouponDiscount.addDiscountCart(cart3, coupon_price_digi_2);
        // CouponDiscount.addDiscountCart(cart3, coupon_price_phys_1);
        // CouponDiscount.addDiscountCart(cart3, coupon_price_phys_2);
        // CouponDiscount.addDiscountCart(cart3, coupon_price_phys_3);

        // System.out.println(CouponDiscount.allDiscountedCarts());

        // CouponDiscount.removeCoupon(2);
        // CouponDiscount.removeCoupon(1);

    //     // Test find discounted cart
    //     CouponDiscount.findDiscountedCart(2);
    //     CouponDiscount.findDiscountedCart(3);
    //     CouponDiscount.findDiscountedCart(1);

        // double discountedFinal;

        // // Test calculate final percentage discount
        // discountedFinal = CouponDiscount.discountCartAmount(cart2);
        // System.out.println("Cart " + cart2.getID() + ": " + cart2.cartAmount());
        // System.out.println("Applied discount: " + CouponDiscount.findCouponCode(cart2) + "%");
        // System.out.println("Cart " + cart2.getID() + " after discount: " + discountedFinal);

    //     discountedFinal = CouponDiscount.discountCartAmount(cart2, coupon_percent_phys_3);
    //     System.out.println(cart2.cartAmount());
    //     System.out.println(coupon_percent_phys_3.getDiscountPercentage() + "%");
    //     System.out.println(discountedFinal);

    //     // Test calculate final fixed price discount
    //     discountedFinal = CouponDiscount.discountCartAmount(cart3, coupon_price_digi_1);
    //     System.out.println(cart3.cartAmount());
    //     System.out.println(coupon_price_digi_1.getDiscountPrice());
    //     System.out.println(discountedFinal);

    //     discountedFinal = CouponDiscount.discountCartAmount(cart3, coupon_price_digi_2);
    //     System.out.println(cart3.cartAmount());
    //     System.out.println(coupon_price_digi_2.getDiscountPrice());
    //     System.out.println(discountedFinal);
    }
}
