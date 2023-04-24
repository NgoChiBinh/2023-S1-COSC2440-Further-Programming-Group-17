package shoppingapp.coupon;
import shoppingapp.product.Product;

import java.text.DecimalFormat;
import java.util.*;

import shoppingapp.SysMessages;
import shoppingapp.cart.CartShopping;
import shoppingapp.cart.CartsAll;

public class CouponDiscount {
    
    private static SysMessages sysMsg = new SysMessages();

    static DecimalFormat ad = new DecimalFormat("#.##");

    private static HashMap<CartShopping, Coupon> discountedCarts = new HashMap<CartShopping, Coupon>();

    public CouponDiscount() {}

    public static HashMap<CartShopping, Coupon> allDiscountedCarts() {
        return discountedCarts;
    } 

    public static CartShopping findDiscountedCart (int cartID) {
        CartShopping cartShopping = CartsAll.findCart(cartID);
        if (discountedCarts.containsKey(cartShopping)) {
            sysMsg.successCouponCartFind(cartShopping, discountedCarts.get(cartShopping));
            return cartShopping;
        } else {
            sysMsg.failCouponCartFind(cartShopping);
            return null;
        }
    }

    public static String findCouponCode (CartShopping cartShopping) {
        Coupon coupon;

        if (discountedCarts.containsKey(cartShopping)) {
            coupon = discountedCarts.get(cartShopping);
            return coupon.getCouponCode();
        } else {
            return "Cart ID " + cartShopping.getID() + " does not have a coupon";
        }
    }

    public static Coupon findCoupon(CartShopping cartShopping) {
        Coupon coupon;
        if (discountedCarts.containsKey(cartShopping)) {
            coupon = discountedCarts.get(cartShopping);
            return coupon;
        } else {
            return null;
        }
    }

    public static boolean addDiscountCart(CartShopping cartShopping, Coupon coupon) {
        if (discountProductInCart(cartShopping, coupon)) {
            if (discountedCarts.containsKey(cartShopping)) {
                discountedCarts.replace(cartShopping, coupon);
                sysMsg.successCouponDup(cartShopping, coupon);
                return true;
            } else {
                discountedCarts.put(cartShopping, coupon);
                sysMsg.successCouponApply(cartShopping, coupon);
                return true;
            }
        } else { 
            sysMsg.failCouponApply(cartShopping, coupon);
            return false;
        }
    }

    public static boolean discountProductInCart(CartShopping cart, Coupon coupon) {
        Set<Product> cartItems = cart.getCart();
        boolean productInCart = false;
        for (Product product : cartItems) {
            if (product.getName() == coupon.getDiscountProduct().getName()) {
                productInCart =  true;
                return productInCart;
            }
        }
        return productInCart;
    }

    public static void removeCoupon (int cartID) {
        CartShopping cartShopping = CartsAll.findCart(cartID);
        if (discountedCarts.containsKey(cartShopping)) {
            System.out.println("\nCoupon code " + discountedCarts.get(cartShopping).getCouponCode() + 
                                " have been removed from cart ID 00" + cartShopping.getID());
            discountedCarts.remove(cartShopping);
        } else { System.out.println("\nShopping Cart ID 00" + cartShopping.getID() + 
                                " currently does not have a coupon applied.");
        }
    }

    public static boolean removeCoupon (CartShopping cartShopping) {
        boolean couponRemoved;
        if (discountedCarts.containsKey(cartShopping)) {
            discountedCarts.remove(cartShopping);
            couponRemoved = true;
        } else {
            couponRemoved = false;
        }
        return couponRemoved;
    }

    public static double discountCartAmount(CartShopping cartShopping) {
        Coupon coupon =  findCoupon(cartShopping);
        double discountedFinal;
        // Calculate Percentage discount
        if (findCoupon(cartShopping) != null) {
            if (coupon.getDiscountPercentage()!= 0.0) {
                discountedFinal = cartShopping.cartAmount() - (cartShopping.cartAmount() * coupon.getDiscountPercentage() / 100);
            } else {
                discountedFinal = cartShopping.cartAmount() - coupon.getDiscountPrice();
            }
            return discountedFinal;
        } else {
            return cartShopping.cartAmount();
        }
    }

}
