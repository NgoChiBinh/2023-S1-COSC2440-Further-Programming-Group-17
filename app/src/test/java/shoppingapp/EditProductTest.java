package shoppingapp;

import static org.junit.Assert.assertEquals;
import org.junit.*;

import shoppingapp.product.ProductDigi;
import shoppingapp.product.ProductPhys;

public class EditProductTest {

    static ProductDigi digital = new ProductDigi("Avast", "Software", 200, 70);

    static ProductPhys physical = new ProductPhys("HP 15", "Laptop", 45, 600, 5);

    @BeforeClass
    public static void setUpDigiChange() {
        String desc = "Anti-Virus";
        int quantity = 150;
        double price = 65.5;

        digital.setDesc(desc);
        digital.setQuantity(quantity);
        digital.setPrice(price);
    }
    
    @Test
    public void testEditDesc() {
        String expected = "Anti-Virus";
        String actual = digital.getDesc();

        assertEquals("Testing edit description", expected, actual);
    }

    @Test
    public void testEditQuantity() {
        int expected = 150;
        int actual = digital.getQuantity();

        assertEquals("Testing edit quantity", expected, actual);
    }

    @Test
    public void testEditPrice() {
        double expected = 65.5;
        double actual = digital.getPrice();
        double delta = 0.1;

        assertEquals("Testing edit price", expected, actual, delta);
    }

    @BeforeClass
    public static void setUpPhysChange() {
        double weight = 6.1;

        physical.setWeight(weight);
    }

    @Test
    public void testEditWeight() {
        double expected = 6.1;
        double actual = physical.getWeight();
        double delta = 0.1;

        assertEquals("Testing edit weight", expected, actual, delta);
    }
}
