package shoppingapp.cart;
import java.util.*;

import shoppingapp.product.Product;
/**
 * @author <Ngo Chi Binh - s3938145>
 */ 
public class CartsAll{
    
    private static ArrayList <CartShopping> cartList = new ArrayList<CartShopping>();

    public CartsAll () {}

    public static void addCart(CartShopping cart) {
        cartList.add(cart);
        sortCart();
    }

    public static CartShopping findCart(int cartNumber) {
        CartShopping cart = null;
        for (CartShopping c : cartList) {
            if (c.getID() == cartNumber) { cart = c;}
        }
        return cart;
    }

    public static void allCarts() {
        sortCart();
        for (int i = 0; i < cartList.size(); i++) {
            System.out.print("\n" + (i + 1) + "." + 
                                "Cart ID 00" + cartList.get(i).getID() +
                                "\n" + "Cart items: " + 
                                "\n"); cartList.get(i).getCartItemsName();
                System.out.print("\n" + "Cart Weight: " + cartList.get(i).cartWeight()+ 
                                 "\n" + "Cart Total: " + cartList.get(i).cartAmount() + 
                                 "\n\n");
        }
    }

    public static ArrayList <CartShopping> cartList() {
        return cartList;
    }

    public static void sortCart() {
        if (cartList != null) {
            Collections.sort((ArrayList<CartShopping>) cartList, (c1, c2) -> Double.compare(c1.cartWeight(), c2.cartWeight()));
        }
    }
}
