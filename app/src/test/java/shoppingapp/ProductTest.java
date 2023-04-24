package shoppingapp;

import static org.junit.Assert.assertEquals;

import org.junit.*;

import shoppingapp.product.ProductDigi;
import shoppingapp.product.ProductPhys;

public class ProductTest {

    static ProductPhys washingMachine;

    static ProductDigi videoGame;

    // TEST CREATING PHYSICAL PRODUCT
    @BeforeClass
    public static void setUpPhysClass() {
        String name = "LG 5kg Top Load";
        String desc = "Washing Machine";
        int quantity = 10;
        double price = 2500;
        double weight = 5;
        washingMachine = new ProductPhys(name, desc, quantity, price, weight);
    }

    @Test
    public void testPhysicalProductName() {
        String expected = "PHYSICAL - LG 5kg Top Load";
        String actual = washingMachine.getName();

        assertEquals("Testing Physical Product Name",expected, actual);
    }

    @Test
    public void testPhysicalProductDesc() {
        String expected = "Washing Machine";
        String actual = washingMachine.getDesc();

        assertEquals("Testing Physical Product Description", expected, actual);
    }

    @Test
    public void testPhysicalProductQuantity() {
        int expected = 10;
        int actual = washingMachine.getQuantity();

        assertEquals("Testing Physical Product Quantity", expected, actual);
    }

    @Test
    public void testPhysicalProductPrice() {
        double expected = 2500;
        double actual = washingMachine.getPrice();
        double delta = 0.1;

        assertEquals("Testing Physical Product Price", expected, actual, delta);;
    }

    @Test
    public void testPhysicalProductWeight() {
        double expected = 5;
        double actual = washingMachine.getWeight();
        double delta = 0.1;

        assertEquals("Testing Physical Product Weight", expected, actual, delta);
    }



    // TEST CREATING DIGITAL PRODUCT
    @BeforeClass
    public static void setUpDigitalClass() {
        String name = "Call of Duty";
        String desc = "Video Game";
        int quantity = 100;
        double price = 60;
        videoGame = new ProductDigi(name, desc, quantity, price);
    }

    @Test
    public void testDigiProductName() {
        String expected = "DIGITAL - Call of Duty";
        String actual = videoGame.getName();

        assertEquals("Testing Physical Product Name",expected, actual);
    }

    @Test
    public void testDigitalProductDesc() {
        String expected = "Video Game";
        String actual = videoGame.getDesc();

        assertEquals("Testing Physical Product Description", expected, actual);
    }

    @Test
    public void testDigitalProductQuantity() {
        int expected = 100;
        int actual = videoGame.getQuantity();

        assertEquals("Testing Physical Product Quantity", expected, actual);
    }

    @Test
    public void testDigitalProductPrice() {
        double expected = 60;
        double actual = videoGame.getPrice();
        double delta = 0.1;

        assertEquals("Testing Physical Product Price", expected, actual, delta);;
    }

    @Test
    public void testDigitalProductWeight() {
        double expected = 0;
        double actual = videoGame.getWeight();
        double delta = 0.1;

        assertEquals("Testing Physical Product Weight", expected, actual, delta);
    }
}
