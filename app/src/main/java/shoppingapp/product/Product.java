package shoppingapp.product;
import java.util.*;
/**
 * @author <Ngo Chi Binh - s3938145>
 */ 
public class Product {

    protected String name;

    protected String desc;

    protected int quantity;

    protected double price;

    protected double weight;

    public Product() {}

    public Product (String name, String desc, int quantity, double price, double weight) {
        this.name = name;
        this.desc = desc;
        this.quantity = quantity;
        this.price = price;
        this.weight = weight;
    }

    public Product (String name, String desc, int quantity, double price) {
        this.name = name;
        this.desc = desc;
        this.quantity = quantity;
        this.price = price;
        this.weight = 0;
    }

    public String getName() { return name; }

    public String getDesc() { return desc; }

    public int getQuantity() { return quantity; }

    public double getPrice() { return price; }

    public double getWeight () {return weight;}

    public void setDesc(String desc) {this.desc = desc;}

    public void setQuantity(int quantity) {this.quantity = quantity;}

    public void incrementQuantity() { ++ this.quantity ; }

    public void decrementQuantity() { -- this.quantity; }

    public void setPrice(double price) {this.price = price;}

    public void setWeight(double weight) {this.weight = weight;}

    @Override
    public String toString() {
        return "Product [name=" + name + ", desc=" + desc + ", quantity=" + quantity + ", price=" + price + "]";
    }

    public class ProductCatalog {
    
        private static ArrayList <Product> catalog = new ArrayList<>();
    
        public ProductCatalog() {}
    
        public static boolean addToCatalog (Product product) {
            return catalog.add(product);
        }
    
        public static ArrayList<Product> getCatalog() {
            return catalog;
        }
    
        public static Product findProduct(String productName) {
            Iterator<Product> iterator = catalog.iterator();
    
            while(iterator.hasNext()) {
                Product product = iterator.next();
                if (product.getName().contains(productName)) {
                    return product;
                }
            }
            return null;
        }
    
        @Override
        public String toString() {
            return "Catalog: " + 
                    "\n" + catalog + 
                    "\n\n";
        }
    }

}
