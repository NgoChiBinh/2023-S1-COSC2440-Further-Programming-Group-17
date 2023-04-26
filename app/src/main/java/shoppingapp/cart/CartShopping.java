package shoppingapp.cart;
import java.util.*;

import shoppingapp.ShippingFee;
import shoppingapp.product.Product;
import shoppingapp.product.Product.ProductCatalog;
/**
 * @author <Ngo Chi Binh - s3938145>
 */ 
public class CartShopping {

    private Set<Product> cartItems = new LinkedHashSet<Product>();

    private int cartID = CartsAll.cartList().size() + 1;

    private double totalCartWeight;

    private double totalBeforeShip;

    private double shippingFee;

    private double totalCost;

    public CartShopping() {}

    public Set<Product> getCart() {
        return cartItems;
    }

    public void getCartItemsName() {
        for (Product product : cartItems) {
            System.out.println("\t" + product.getName() + " : " + product.getPrice());
            if (product.getWeight() > 0) 
            {
                System.out.println("\t" + product.getWeight() + "kg");
            }
        }
    }

    public boolean productInCart(Product searchProduct) {
        boolean inCart = false;
        for (Product product : cartItems) {
            if(searchProduct.getName().equals(product.getName()))
            {
                inCart = true;
                return inCart;
            }
        }
        return inCart;
    }

    public int getID() {
        return cartID;
    }

    public double cartWeight() {
        return totalCartWeight;
    }

    public void setTotalCart(double num) {
        this.totalCost = num;
    }

    public void setTotalCartWeight(double num) {
        this.totalCartWeight = num;
    }

    public void setTotalBeforeShip(double num) {
        this.totalBeforeShip = num;
    }

    public boolean addItem(String productName) {
        boolean inCart;
        Product product = ProductCatalog.findProduct(productName);
        if (product != null) {
            if (product.getQuantity() != 0) {
                if (inCart = cartItems.add(product)) {
                    product.decrementQuantity();
                    totalBeforeShip += product.getPrice();
                    totalCartWeight += product.getWeight();
                    cartAmount();
                    return inCart;
                }
            } 
        }
        return inCart = false;
    }

    public boolean removeItem(String productName) {
        boolean removed;
        Product product = ProductCatalog.findProduct(productName);
        if (product != null) {
            if (product.getQuantity() != 0) {
                if (removed = cartItems.remove(product)) {
                    product.incrementQuantity();
                    totalBeforeShip -= product.getPrice();
                    totalCartWeight -= product.getWeight();
                    cartAmount();
                    return removed;
                }
            }
        }
        return removed = false;
    }

    public double cartAmount() {
        shippingFee = ShippingFee.calcFee(totalCartWeight);
        this.totalCost = totalBeforeShip + shippingFee;
        return totalCost;
    }

    public void updateTotalCost() {
        setTotalBeforeShip(0);
        setTotalCart(0);
        setTotalCartWeight(0);
        for (Product product : cartItems) {
            totalBeforeShip += product.getPrice();
            totalCartWeight += product.getWeight();
        }
        cartAmount();
    }

    @Override
    public String toString() {
        return "\nShopping Cart ID " + String.format("%03d",cartID) + 
                "\n ITEMS IN CART " + cartItems + 
                "\n Total Cart's Weight: " + totalCartWeight + " kg" + 
                "\n Cost before shipping fee: " + "$" + totalBeforeShip + 
                "\n Final: " + "$" + totalCost + 
                "\n";
    }
}
