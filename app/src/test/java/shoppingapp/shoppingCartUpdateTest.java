package shoppingapp;

import static org.junit.Assert.assertEquals;

import org.junit.*;

import shoppingapp.cart.CartShopping;
import shoppingapp.product.Product.ProductCatalog;
import shoppingapp.product.ProductDigi;
import shoppingapp.product.ProductPhys;

public class shoppingCartUpdateTest {

    static ProductDigi digital_1 = new ProductDigi("Malwarebytes", "Software", 100, 200);

    static ProductDigi digital_2 = new ProductDigi("Norton", "Software", 2, 150);

    static ProductPhys physical_1 = new ProductPhys("HP Envy", "Laptop", 3, 200, 5);

    static CartShopping cart = new CartShopping();

    @BeforeClass
    public static void setUpCatalog() {
        ProductCatalog.addToCatalog(digital_1);
        ProductCatalog.addToCatalog(digital_2);
        ProductCatalog.addToCatalog(physical_1);

        cart.addItem(digital_1.getName());
        cart.addItem(digital_2.getName());

    }
    
    @Test
    public void testUpdateTotalCartAmount() {
        digital_1.setPrice(100);
        cart.updateTotalCost();
        double expected = 250;
        double actual = cart.cartAmount();
        double delta = 0.1;

        assertEquals("Test cart total amount update once ",expected, actual, delta);
    }
}
