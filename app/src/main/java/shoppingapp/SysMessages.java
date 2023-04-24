package shoppingapp;

import shoppingapp.cart.CartShopping;
import shoppingapp.coupon.Coupon;

/**
 * @author <Ngo Chi Binh - s3938145>
 */ 
public class SysMessages {
    
    public SysMessages() {}

    // Add product messages
    public void successMsg(String productName, String category) {
        System.out.println("Product " + productName + " successfully added to " + category);
    }
    public void  failMsg(String productName, String category) {
        System.out.println("Failed to add " + productName + " to " + category);
        System.out.println("Please enter a valid/available product");
    }


    // Remove product from shopping cart messages
    public void successRemoveMsg(String productName, String category) {
        System.out.println("Product " + productName + " successfully removed from cart");
    }
    public void failRemoveMsg(String productName, String category) {
        System.out.println("Failed to add " + productName + " to " + category);
    }


    // Add coupon to shopping cart messages
    public void successCouponApply(CartShopping cart, Coupon coupon) {
        System.out.println(coupon.getCouponCode() + " successfully applied to shopping cart ID 00" + cart.getID());
    }
    public void failCouponApply(CartShopping cart, Coupon coupon) {
        System.out.println("Product " + coupon.getDiscountProduct().getName() + " is not found in Shopping Cart ID 00" + cart.getID());
    }

    // Find shopping cart with coupon messages
    public void successCouponCartFind(CartShopping cart, Coupon coupon) {
        System.out.println("Shopping cart ID 00" + cart.getID() + " : " + coupon.getCouponCode());
    }
    public void failCouponCartFind(CartShopping cart) {
        System.out.println("Shopping cart ID 00" + cart.getID() + " currently does not have a coupon applied");
    }

    // Replace existing coupon message
    public void successCouponDup(CartShopping cart, Coupon coupon) {
        System.out.println("Coupon Code " + coupon.getCouponCode() + " successfully replace existing coupon of shopping cart ID 00" + cart.getID());
    }


    // Fail to find cart with ID number message
    public void failCartFind(int cartID) {
        System.out.println("Shopping Cart with the ID" + cartID + " not found");
        System.out.println("Please try again, or enter 'q' to quit");
    }

    // Fail to find product with name message
    public void failProductFind(String productName) {
        System.out.println("Product " + productName + " not found");
    }



}
