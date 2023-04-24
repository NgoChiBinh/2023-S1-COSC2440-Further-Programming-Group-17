package shoppingapp;

import static org.junit.Assert.assertEquals;

import org.junit.*;

import shoppingapp.cart.CartShopping;
import shoppingapp.cart.CartsAll;
import shoppingapp.coupon.Coupon;
import shoppingapp.coupon.CouponDiscount;
import shoppingapp.coupon.CouponPercent;
import shoppingapp.coupon.CouponPrice;
import shoppingapp.product.ProductDigi;
import shoppingapp.product.ProductPhys;
import shoppingapp.product.Product.ProductCatalog;

public class CouponTest {
    
    static ProductDigi digital_1 = new ProductDigi("Malwarebytes", "Software", 100, 200);
    static ProductDigi digital_2 = new ProductDigi("Norton", "Software", 2, 150);
    static ProductPhys physical_1 = new ProductPhys("HP Envy", "Laptop", 3, 200, 5);
    static ProductPhys physical_2 = new ProductPhys("Dell XP9", "Laptop", 14, 350, 6.7);
    static ProductPhys physical_3 = new ProductPhys("Samsung Galaxy", "Smartphone", 3, 200, 2);

    static CartShopping cart1 = new CartShopping();
    static CartShopping cart2 = new CartShopping();
    static CartShopping cart3 = new CartShopping();

    
    static Coupon coupon_percent_digi_1 = new CouponPercent(digital_1, 40);
    static Coupon coupon_percent_digi_2 = new CouponPercent(digital_2, 30);
    static Coupon coupon_percent_phys_1 = new CouponPercent(physical_1, 50);
    static Coupon coupon_percent_phys_2 = new CouponPercent(physical_2, 60);
    static Coupon coupon_percent_phys_3 = new CouponPercent(physical_3, 70);


    static Coupon coupon_price_digi_1 = new CouponPrice(digital_1, 20.99);
    static Coupon coupon_price_digi_2 = new CouponPrice(digital_2, 30.59);
    static Coupon coupon_price_phys_1 = new CouponPrice(physical_1, 40.44);
    static Coupon coupon_price_phys_2 = new CouponPrice(physical_2, 59.99);
    static Coupon coupon_price_phys_3 = new CouponPrice(physical_3, 60);

    @BeforeClass
    public static void setUpCoupon() {
        // Add all product to catalog
        ProductCatalog.addToCatalog(digital_1);
        ProductCatalog.addToCatalog(digital_2);
        ProductCatalog.addToCatalog(physical_1);
        ProductCatalog.addToCatalog(physical_2);
        ProductCatalog.addToCatalog(physical_3);


        // Set up shopping carts
        // Cart ID 001: Physical + Digital items
        cart1.addItem(digital_1.getName());
        cart1.addItem(physical_1.getName());
        cart1.addItem(digital_2.getName());
        cart1.addItem(physical_2.getName());


        // Cart ID 002: Only Physical Items
        cart2.addItem(physical_1.getName());
        cart2.addItem(physical_3.getName());


        // Cart ID 003: Only Digital Items
        cart3.addItem(digital_1.getName());
        cart3.addItem(digital_2.getName());


        // Check out all carts
        CartsAll.addCart(cart1);
        CartsAll.addCart(cart2);
        CartsAll.addCart(cart3);

        // Apply coupon to shopping cart ID 002
        CouponDiscount.addDiscountCart(cart2, coupon_percent_phys_1);

        // Apply coupon to shopping cart ID 003
        CouponDiscount.addDiscountCart(cart3, coupon_percent_phys_1);
    }
    public static void main(String[] args) {

        cart2.addItem(physical_1.getName());
        cart2.addItem(physical_3.getName());

        CartsAll.addCart(cart2);

        CouponDiscount.addDiscountCart(cart2, coupon_percent_phys_1);

        // Test calculate final percentage discount
        double discountedFinal = CouponDiscount.discountCartAmount(cart2);
        System.out.println("Cart " + cart2.getID() + ": " + cart2.cartAmount());
        System.out.println("Applied discount: " + CouponDiscount.findCouponCode(cart2) + "%");
        System.out.println("Cart " + cart2.getID() + " after discount: " + discountedFinal);

    }

    @Test
    public void TestApplyCouponToCart() {
        boolean expected = true;
        boolean actual = CouponDiscount.addDiscountCart(cart2, coupon_price_phys_1);

        assertEquals("Test adding coupon to cart with discounted product"
                    ,expected, actual);
    }

    @Test
    public void TestApplyCouponToCartFailed() {
        boolean expected = false;
        boolean actual = CouponDiscount.addDiscountCart(cart3, coupon_percent_phys_1);

        assertEquals("Test adding coupon to cart without discounted product"
                    ,expected, actual);
    }

    @Test
    public void TestOneCouponPerCart() {
        CouponDiscount.addDiscountCart(cart2, coupon_percent_phys_3);
        String expected = coupon_percent_phys_3.getCouponCode();
        String actual = CouponDiscount.findCouponCode(cart2);

        assertEquals("Test replace coupon on the same cart " + 
                        "cart ID 002 old coupon code: " + coupon_price_phys_1.getCouponCode()
                        , expected, actual);
    }

    @Test 
    public void TestReplaceCoupon() {
        CouponDiscount.addDiscountCart(cart2, coupon_percent_phys_1);
        String expected = coupon_percent_phys_1.getCouponCode();
        String actual = CouponDiscount.findCouponCode(cart2);

        assertEquals("Test replace coupon on the same cart " + 
                        "cart ID 002 old coupon code: " + coupon_price_phys_1.getCouponCode()
                        , expected, actual);
    }

    @Test
    public void TestRemoveCoupon() {
        boolean expected = true;
        boolean actual = CouponDiscount.removeCoupon(cart2);

        assertEquals("Test remove coupon from cart"
        , expected, actual);
    }

    @Test 
    public void TestPercentageDiscount() {
        CouponDiscount.addDiscountCart(cart2, coupon_percent_phys_1);
        double expected = 200.35;
        double actual = CouponDiscount.discountCartAmount(cart2);
        double delta = 0.1;

        assertEquals("Test percentage discount"
        , expected, actual, delta);
    } 

    @Test
    public void TestPriceDiscount() {
        CouponDiscount.addDiscountCart(cart2, coupon_price_phys_1);
        double expected = 360.26;
        double actual = CouponDiscount.discountCartAmount(cart2);
        double delta = 0.1;

        assertEquals("Test fixed price discount"
        , expected, actual, delta);
    }
}
