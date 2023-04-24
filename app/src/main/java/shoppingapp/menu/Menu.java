package shoppingapp.menu;
import java.util.Scanner;

import shoppingapp.SysMessages;
import shoppingapp.cart.CartShopping;
import shoppingapp.cart.CartsAll;
import shoppingapp.product.Product;
import shoppingapp.product.ProductDigi;
import shoppingapp.product.ProductPhys;
import shoppingapp.product.Product.ProductCatalog;
/**
 * @author <Ngo Chi Binh - s3938145>
 */ 
public class Menu {
    
    private static Scanner input = new Scanner(System.in);

    private static int choice;

    public Menu() {}    

    SysMessages sysMes = new SysMessages();

    String categoryCatalog = "Catalog";
    
    String categoryCart = "Cart";

    String productName;

    public void mainMenu() {

        do {
            System.out.println("1. Create new Product");
            System.out.println("2. Edit Products");
            System.out.println("3. Create new cart");
            System.out.println("4. Select existing cart");
            System.out.println("5. Display all carts");

            choice = Integer.parseInt(input.nextLine());      

            switch (choice) {
                case 1:
                    productMenu();
                    break;
                case 2:
                    editMenu();
                    break;
                case 3:
                    createCart();
                    break;
                case 4:
                    existingCart();
                    break;
                case 5:
                    allCart();
                    break;
            }

        } while (choice > 5 || choice < 1);
        mainMenu();
    }


    public void productMenu() {
            System.out.println("Product Name");
            String name = input.nextLine();

            System.out.println("Description");
            String desc = input.nextLine();

            System.out.println("Available Quatity");
            int quantity = Integer.parseInt(input.nextLine());

            System.out.println("Price");
            double price = Double.parseDouble(input.nextLine());

            do {

                System.out.println("Physical or Digital product");
                System.out.println("0. Back");
                System.out.println("1. Physical");
                System.out.println("2. Digital");
        
                choice = Integer.parseInt(input.nextLine());
        
                switch (choice) {
                    case 0:
                        productMenu();
                        break;
                    case 1:
                        System.out.println("Weight");
                        double weight = Double.parseDouble(input.nextLine());

                        ProductPhys physical = new ProductPhys(name, desc, quantity, price, weight);

                        if (ProductCatalog.addToCatalog(physical)) {
                            sysMes.successMsg(name, categoryCatalog);
                        } else {
                            sysMes.failMsg(name, categoryCatalog);
                        }
                        break;
                    case 2:
                        ProductDigi digital = new ProductDigi(name, desc, quantity, price);

                        if (ProductCatalog.addToCatalog(digital)) {
                            sysMes.successMsg(name, categoryCatalog);
                        } else {
                            sysMes.failMsg(name, categoryCatalog);
                        }
                        break;
                    default:
                        System.out.println("Invalid input");
                }
            } while (choice > 3 || choice < 0);
        mainMenu();
    }


    public void editMenu() {
            System.out.println(ProductCatalog.getCatalog());
            System.out.println("Which product do you want to edit?");
            String productName = input.nextLine();

            Product product = ProductCatalog.findProduct(productName);

            if (product == null) {
                sysMes.failProductFind(productName);
                editMenu();
            }
            do {
                System.out.println("Which aspect of " + productName + " do you want to change?");
                System.out.println("0. Back");
                System.out.println("1. Description");
                System.out.println("2. Quantity");
                System.out.println("3. Price");
                System.out.println("4. Weight");

                choice = Integer.parseInt(input.nextLine());

                switch (choice) {
                    case 0:
                        editMenu();
                        break;
                    case 1:
                        System.out.println("Change Description");
                        String desc = input.nextLine();
                        product.setDesc(desc);
                        break;
                    case 2:
                        System.out.println("Change Quantity");
                        int quantity = Integer.parseInt(input.nextLine());
                        product.setQuantity(quantity);
                        updateCarts();
                        break;
                    case 3:
                        System.out.println("Change Price");
                        double price = Double.parseDouble(input.nextLine());
                        product.setPrice(price);
                        updateCarts();
                        break;
                    case 4:
                        System.out.println("Change Weight");
                        double weight = Double.parseDouble(input.nextLine());
                        product.setWeight(weight);
                        updateCarts();
                        break;
                }
            }while (choice > 4 || choice < 0);
        mainMenu();
    }

    public void updateCarts() {
        for (CartShopping CartShopping : CartsAll.cartList()) {
            CartShopping.updateTotalCost();
        }
    }

    public void createCart() {

        CartShopping cart = new CartShopping();

        CartsAll.addCart(cart);

        addToCartMenu(cart);

    }


    public void existingCart() {

        int index;

        CartsAll.allCarts();

        System.out.println("Which cart do you want to access?");
        System.out.println("Input cart ID");
        index = Integer.parseInt(input.nextLine());

        while (CartsAll.findCart(index) == null) {
            sysMes.failCartFind(index);
            existingCart();
        }

        CartShopping cart = CartsAll.findCart(index);
        System.out.println(cart);

        do {
            System.out.println("Would you like to add or remove an item from the cart?");
            System.out.println("1. Add new item to Cart");
            System.out.println("2. Remove item from Cart");
            choice = Integer.parseInt(input.nextLine());
            switch(choice) {
                case 1:
                    addToCartMenu(cart);
                    break;
                case 2:
                    removeFromCartMenu(cart);
                    break;
            }
        } while (choice > 2 || choice < 1);
        mainMenu();
    }

    public void allCart() {
        CartsAll.allCarts();
        System.out.println("1. Back to main menu");
        choice = Integer.parseInt(input.nextLine());

        do { mainMenu();} while(choice != 1);
    }

    public void addToCartMenu(CartShopping cart) {
        do {
            System.out.println(ProductCatalog.getCatalog());
            System.out.println("Enter name of product you want to add to cart");
            System.out.println("Or enter 'q' if you're done");
            productName = input.nextLine();

            if (cart.addItem(productName)) {
                sysMes.successMsg(productName, categoryCart);
            } else {
                sysMes.failMsg(productName, categoryCart);
            }

        }while (productName == "q");
        System.out.println(cart);
        mainMenu();
    }

    public void removeFromCartMenu(CartShopping cart) {
        do {
            System.out.println(cart);
            System.out.println("Enter name of product you want to remove");
            System.out.println("Or enter 'q' if you're done");
            productName = input.nextLine();

            if (cart.removeItem(productName)) {
                sysMes.successRemoveMsg(productName, categoryCart);
            } else {
                sysMes.failRemoveMsg(productName, categoryCart);
            }

        }while (productName == "q");
        mainMenu();
    }

}
