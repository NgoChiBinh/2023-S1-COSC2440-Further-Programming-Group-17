import cart.ShoppingCart;
import domain.model.DigitalProduct;
import domain.model.PhysicalProduct;
import domain.model.Product;
import service.ProductManager;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 */
public class Main {
  
  static ProductManager productManager = new ProductManager();
  static Set<ShoppingCart> shoppingCarts = new TreeSet<>();
  
  public static void main(String[] args) {
    loop();
  }
  
  static void loop() {
    Scanner scanner = new Scanner(System.in);
    loop : while (true) {
      System.out.println("WELCOME TO THE SHOP");
      System.out.println("Please select: ");
      System.out.println("  1. Create a product");
      System.out.println("  2. Edit a product");
      System.out.println("  3. Create a shopping cart");
      System.out.println("  4. Display all shopping carts");
      System.out.println("  0. Quit");
      System.out.print("Your choice: ");
      
      int c = scanner.nextInt();
      scanner.nextLine();
      switch (c) {
        case 1: {
          Product product = createProduct(scanner);
          productManager.save(product);
          System.out.println("Product created");
          System.out.println();
          break;
        }
        case 2: {
          editProduct(scanner);
          System.out.println("Product edited");
          System.out.println();
          break;
        }
        case 3: {
          loopShoppingCart(scanner);
          System.out.println();
          break;
        }
        case 4: {
          shoppingCarts.forEach(System.out::println);
          System.out.println();
          break;
        }
        default:
          break loop;
      }
    }
  }
  
  private static void loopShoppingCart(Scanner scanner) {
    ShoppingCart cart = new ShoppingCart();
    loop : while (true) {
      System.out.println("Creating a shopping cart...");
      System.out.println("Please select: ");
      System.out.println("  1. Add a product");
      System.out.println("  2. Remove a product");
      System.out.println("  3. Display amount");
      System.out.println("  0. Back");
      System.out.print("Your choice: ");
      
      int c2 = scanner.nextInt();
      scanner.nextLine();
      switch (c2) {
        case 1: {
          String name = inputStringValue("Input name: ", scanner);
          if(cart.addItem(name)) System.out.println("Item added");
          else System.out.println("Item is not added");
          System.out.println();
          break;
        }
        case 2: {
          String name = inputStringValue("Input name: ", scanner);
          try {
            if(cart.removeItem(name)) System.out.println("Item removed");
            else System.out.println("Item is not removed");
            System.out.println();
          } catch (Exception e) {
            System.err.println(e.getMessage());
            System.out.println("Item is not removed");
            System.out.println();
          }
          
          break;
        }
        case 3: {
          System.out.printf("Amount of this cart is %f%n", cart.cartAmount());
          System.out.println();
          break;
        }
        default:
          shoppingCarts.add(cart);
          break loop;
      }
    }
  }
  
  private static Product createProduct(Scanner scanner) {
    System.out.println("Creating a product...");
  
    int tmp = inputType(scanner);
    String name = inputName(scanner, null, "");
    String description = inputStringValue("Input description: ", scanner);
    int quantity = inputIntValue("Input quantity: ", scanner);
    double price = inputDoubleValue("Input price: ", scanner);
    double percentage = inputDoubleValue("Input tax (percentage, no need % character): ", scanner);
  
    Product.Type type = Product.Type.fromCode(tmp);
    if(type == Product.Type.DIGITAL) {
      return new DigitalProduct(name, description, quantity, price, Product.Tax.fromPercentage(percentage));
    }
  
    double weight = inputDoubleValue("Input weight: ", scanner);
    return new PhysicalProduct(name, description, quantity, price, weight, Product.Tax.fromPercentage(percentage)) ;
  }
  
  private static void editProduct(Scanner scanner) {
    System.out.println("Editing a product...");
  
    String name = inputStringValue("Input name: ", scanner);
    Product product;
    while ((product = productManager.getByName(name)) == null) {
      name = inputStringValue("Could not find product with name %s, please input correct name: ".formatted(name), scanner);
    }
    
    String oldName = product.getName();
    
    String newName = inputName(scanner, product, "new");
    product.setName(newName);
    String description = inputStringValue("Input description: ", scanner);
    product.setDescription(description);
    int quantity = inputIntValue("Input quantity: ", scanner);
    product.setQuantity(quantity);
    double price = inputDoubleValue("Input price: ", scanner);
    product.setPrice(price);
  
    Product.Type type = product.getType();
    if(type == Product.Type.PHYSICAL) {
      double weight = inputDoubleValue("Input weight: ", scanner);
      ((PhysicalProduct)product).setWeight(weight);
    }
    
    productManager.remove(oldName);
    productManager.save(product);
  
  }
  
  private static int inputType(Scanner scanner) {
    int tmp = inputIntValue("Choose type (1 for Digital, 2 for Physical): ", scanner);
    scanner.nextLine();
    while(tmp < 1 || tmp > 2) {
      System.out.print("Invalid type, please choose another type: ");
      tmp = scanner.nextInt();
      scanner.nextLine();
    }
    
    return tmp;
  }
  
  private static String inputStringValue(String desc, Scanner scanner) {
    System.out.print(desc);
    return scanner.nextLine();
  }
  
  private static double inputDoubleValue(String desc, Scanner scanner) {
    System.out.print(desc);
    return scanner.nextDouble();
  }
  
  private static int inputIntValue(String desc, Scanner scanner) {
    System.out.print(desc);
    return scanner.nextInt();
  }
  
  private static String inputName(Scanner scanner, Product current, String tag) {
    String name = inputStringValue("Input %s name: ".formatted(tag), scanner);
//    while ((current != null && !name.equals(current.getName()) && productManager.contains(name)) || (productManager.contains(name))) {
    while (current == null ? productManager.contains(name) : (!name.equals(current.getName()) && productManager.contains(name))) {
      System.out.print("Duplicated name, please input another name: ");
      name = scanner.nextLine();
    }
    return name;
  }
  
}
