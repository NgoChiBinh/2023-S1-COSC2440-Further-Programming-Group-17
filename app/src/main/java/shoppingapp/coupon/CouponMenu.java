package shoppingapp.coupon;

import java.util.*;

import shoppingapp.SysMessages;
import shoppingapp.cart.CartShopping;
import shoppingapp.cart.CartsAll;
import shoppingapp.coupon.Coupon.CouponCatalog;
import shoppingapp.menu.Menu;
import shoppingapp.product.Product;
import shoppingapp.product.Product.ProductCatalog;

public class CouponMenu {

    private static Menu menu;

    private static SysMessages sysMsg = new SysMessages();

    private static Scanner input = new Scanner(System.in);

    private static int choice;

    private static String yn;

    public CouponMenu() {}

    public static void couponMenu()
    {
        
        do {
            System.out.println("What do you want to do?");
            System.out.println("0. Main Menu");
            System.out.println("1. Create new Coupon");
            System.out.println("2. Apply a coupon to an existing cart");
            System.out.println("3. Remove a coupon from an existing cart");
            System.out.println("4. Show all available coupons");

            while (!input.hasNextInt())
            {
                sysMsg.invalidInput();
                input.next();

            }
            choice = input.nextInt();


            switch (choice) {
                case 0:
                    menu.mainMenu();
                    break;
                
                case 1:
                    generateCouponMenu();
                    break;

                case 2:
                    addCouponToCart();

                case 3:
                    removeFromCart();
                    break;

                case 4:
                    couponCatalog();
            }
        } while (choice > 1 || choice < 0);
    }

    public static void generateCouponMenu() 
    {
        
        Product selectedProduct;

        do {
            System.out.println("Which product would you like to create a coupon for? ");
            String productName = input.next();
            selectedProduct = ProductCatalog.findProduct(productName);
            
            if(selectedProduct == null)
            {
                sysMsg.failProductFind(productName);
                generateCouponMenu();
            }

            System.out.println("You're about to create a coupon for the product " + selectedProduct);
            sysMsg.confirmationYN();
            yn = input.next().toUpperCase();
        } while (yn.equals("N") || !yn.equals("Y"));
        // Menu keep looping as long as the answer isn't "y" or "Y"
        
        do 
        {
            System.out.println("Would you like this coupon to to be a: ");
            System.out.println("0. Back");
            System.out.println("1. Percentage discount coupon");
            System.out.println("2. Fixed price discount coupon");

            while(!input.hasNextInt())
            {
                sysMsg.invalidInput();
                input.next();
            }
            choice = input.nextInt();

            switch(choice)
            {
                case 0:
                    generateCouponMenu();
                    break;
                case 1:
                    generatePercentCouponMenu(selectedProduct); 
                    break;

                case 2:
                    generatePriceCouponMenu(selectedProduct); 
                    break;
            }
        } while (choice > 2 || choice < 0);

    }


    private static void generatePercentCouponMenu(Product selectedProduct)
    {
        int discountPercent;

        do {
            do {
                System.out.println("What percentage do you want your discount to be?");
                while(!input.hasNextInt())
                {
                    sysMsg.invalidInput();
                    input.next();
                }
                discountPercent = input.nextInt();
            } while (discountPercent <= 0);

            System.out.println("You're about to create a " + discountPercent + "% discount coupon for " + selectedProduct.getName());
            sysMsg.confirmationYN();
            yn = input.next().toUpperCase();
        } while (yn.equals("N") || !yn.equals("Y"));

        CouponPercent newCoupon = new CouponPercent(selectedProduct, discountPercent); 

        if(!CouponCatalog.addCouponToCatalog(newCoupon))
        {
            sysMsg.failCouponAdd(newCoupon);
        
        }
        couponMenu();
    }


    private static void generatePriceCouponMenu(Product selectedProduct)
    {
        double discountPrice;

        do {
            do {
                System.out.println("What the amount do you want your discount to be?");
                while(!input.hasNextDouble())
                {
                    sysMsg.invalidInput();
                    input.next();
                }
                discountPrice = input.nextDouble();
            } while (discountPrice <= 0);
            
            System.out.println("You're about to create a $" + discountPrice + " discount coupon for " + selectedProduct.getName());
            sysMsg.confirmationYN();
            yn = input.next().toUpperCase();
        } while (yn.equals("N") || !yn.equals("Y"));

        CouponPrice newCoupon = new CouponPrice(selectedProduct, discountPrice); 
        if(!CouponCatalog.addCouponToCatalog(newCoupon))
        {

            sysMsg.failCouponAdd(newCoupon);
        }
        couponMenu();
    }


    public static void addCouponToCart()
    {
        Coupon coupon;
        String couponCode;
        String productToDiscount;
        Product selectedProduct;
        Set<Coupon> productCoupons;

        System.out.println("Adding coupon to cart");
        System.out.println("Which cart do you want to apply a coupon to?");
        CartsAll.allCarts();

        do {
            System.out.println("Enter a cart ID from 001-00" + CartsAll.cartList().size() + 1);
            while(!input.hasNextInt())
            {
                sysMsg.invalidInput();
                input.next();
            }
            choice = input.nextInt();
        } while (choice > CartsAll.cartList().size() + 1 || choice < 1);

        CartShopping cartToAdd =  CartsAll.findCart(choice);

        do 
        {
            do {
                System.out.println(cartToAdd);
                System.out.println("Which product would you like to find a coupon for?");
                while (!input.hasNext())
                {
                    sysMsg.invalidInput();
                    input.next();
                }
                productToDiscount = input.next();
    
                selectedProduct =  ProductCatalog.findProduct(productToDiscount);

                productCoupons = CouponCatalog.findCoupons(selectedProduct.getName());

                if (selectedProduct == null || !cartToAdd.productInCart(selectedProduct))
                {
                    sysMsg.failProductFind(productToDiscount);
                } else {
                    if (productCoupons == null)
                    {
                        System.out.println("No coupon(s) found for the products in Cart ID 00" + cartToAdd.getID());;
                    }
                }

            } while (selectedProduct == null && productCoupons == null && !cartToAdd.productInCart(selectedProduct));

            System.out.println("Is this the product you want a discount for?");
            System.out.println(selectedProduct.getName());
            System.out.println(selectedProduct.getPrice());
            while (!input.hasNext())
            {
                sysMsg.invalidInput();
                input.next();
            }
            yn = input.next().toUpperCase();

        } while (yn.equals("N") || !yn.equals("Y"));

        System.out.println(productCoupons);

        do {
            do {
                System.out.println("Which coupon would you like to add to Cart ID 00" + cartToAdd.getID());
                while (!input.hasNext())
                    {
                        sysMsg.invalidInput();
                        input.next();
                    }
                couponCode = input.next();
        
                coupon = CouponCatalog.findCouponBasedOnCode(couponCode);

                if(coupon == null){
                    System.out.println("Failed to find coupon with code " + couponCode);
                }
            } while (coupon == null);
            System.out.println("Are you sure you want to apply coupon: " + coupon.getCouponCode() + 
                                " to " + "Cart ID 00" + cartToAdd.getID());
            yn = input.next().toUpperCase();
        } while (yn.equals("N") || !yn.equals("Y"));
 
        CouponDiscount.addDiscountCart(cartToAdd, coupon);
        couponMenu();
    }


    public static void removeFromCart()
    {   
        do {
            System.out.println("Shopping carts with discounts: " + "\n");
            CouponDiscount.allDiscountedCarts();
            System.out.println("Which cart would you like to remove the coupon from?");
            System.out.println("Select cart ID from 001-00" + CartsAll.cartList().size() + 1);

            while(!input.hasNextInt())
            {
                sysMsg.invalidInput();
                input.next();
            }
            choice = input.nextInt();
        } while (choice > CartsAll.cartList().size() + 1 || choice < 1);

        do {
            sysMsg.confirmationYN();

            while(!input.hasNext())
            {
                sysMsg.invalidInput();
                input.next();
            }
            yn = input.next();
        } while (yn.equals("N") || !yn.equals("Y"));

        System.out.println("Removing coupon from Cart ID 00" + choice);

        CouponDiscount.removeCoupon(choice);

        couponMenu();
    }


    public static void couponCatalog()
    {
        CouponCatalog.displayCouponsCatalog();
        couponMenu();
    }
}
