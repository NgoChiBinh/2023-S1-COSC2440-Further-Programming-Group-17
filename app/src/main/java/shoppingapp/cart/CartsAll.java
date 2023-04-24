package shoppingapp.cart;
import java.util.*;
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
            System.out.println("\n" + (i + 1) + "." + cartList.get(i) + "\n");
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
