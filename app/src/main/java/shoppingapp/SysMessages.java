package shoppingapp;

import java.util.Scanner;

import shoppingapp.cart.CartShopping;
import shoppingapp.coupon.Coupon;
import shoppingapp.coupon.CouponPercent;
import shoppingapp.coupon.CouponPrice;
import shoppingapp.product.Product;

/**
 * @author <Ngo Chi Binh - s3938145>
 */ 
public class SysMessages {
    
    public SysMessages() {}

    public void confirmationYN()
    {
        System.out.println("Are you sure ? Y/N");
    }


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

    // Fail to find product with name 
    public void failProductFind(String productName) {
        System.out.println("Product " + productName + " not found");
    }

    // Fail to find product in current cart
    public void failProductInCartFind(CartShopping cartShopping, Product product)
    {
        System.out.println("Product" + product.getName() + " is not found in Cart ID 00" + cartShopping.getID());
    }


    // Creating coupon messages
    public void successCouponAdd(CouponPercent newCoupon)
    {
        System.out.println("Coupon" + newCoupon.getCouponCode() + ", providing " + 
                            newCoupon.getDiscountPercentage() + "% to " + 
                            newCoupon.getDiscountProduct().getName());        
    }
    public void successCouponAdd (CouponPrice newCoupon)
    {
        System.out.println("Coupon" + newCoupon.getCouponCode() + ", providing " + 
                            newCoupon.getDiscountPrice() + "$ to " + 
                            newCoupon.getDiscountProduct().getName());
    }

    public void failCouponAdd (Coupon newCoupon)
    {
        System.out.println("Failed to add coupon " + newCoupon.getCouponCode() + " to Coupon Catalog");
        System.out.println("Coupon " + newCoupon.getCouponCode() + " already exist");
    }

    // Invalid input
    public void invalidInput()
    {
        System.out.println("Your input was invalid");
    }


}
