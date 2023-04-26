package org.example.libs;

import org.example.cart.Cart;
import org.example.product.PhysicalProduct;
import org.example.product.Product;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;

public class CartHelper{
    static Random random = new Random();
    private static final String[] DEFAULT_TYPE = {"Small", "Medium", "Large"};
    private static List<Product> carts = new ArrayList<>();
    static final char[] codes = {'P', 'D'};

    public static String serializeCartHelper(Cart cart){
        return String.format("%s,%.2f,%s,%d",
                cart.getCarts(),
                cart.getTotalWeight(),
                cart.getTypeOfCart(),
                cart.getQuantityOfProducts());
    }

    public static List<Product> getCarts(Path path) throws IOException{
        carts = Files.lines(path, StandardCharsets.UTF_8)
                .map(line -> {
                    String[] pattern = line.split(",");
                    String productName = pattern[0];
                    int quantity = Integer.parseInt(pattern[1]);
                    String greeting = pattern[2];
                    Object value = pattern[3];
                    Number coupon = null;
                    Cart cart = null;
                    Product product = null;

                    try{
                        product = ProductHelpers.findProductByName(path, productName);
                        if(value instanceof Double){
                            coupon = (Double)value;
                        }else if(value instanceof Integer){
                            coupon = (Integer)value;
                        }

                    }catch (NumberFormatException err){
                        System.err.println();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    return product;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return carts;
    }
//
//    public static String getStringOfCart(Path path, String productName) throws IOException {
//        for(String cart: getStringCarts(path)){
//            if(cart.split(",")[0].equalsIgnoreCase(productName)){
//                return cart;
//            }
//        }
//
//        return " ";
//    }

    public static void generateAllCartInstance(Path path, int numberOfCarts) throws IOException {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < numberOfCarts; i++){
            sb.append(serializeCartHelper(generateCartInstance())).append("\n");
        }

        String fileName = String.valueOf(path.getFileName());
        String folder = path.getParent().getParent().getFileName().toString();

        Helpers.createFile(folder, fileName);

        if(Files.readAllLines(path).size() == 0){
            Files.write(path, Collections.singletonList(sb.toString().trim()), StandardOpenOption.APPEND);
        }else{
            System.err.println("Your 10 default carts are initially created");
        }
    }

    public static Cart generateCartInstance() throws IOException {
        Cart cart = new Cart();
        cart.setTypeOfCart(DEFAULT_TYPE[random.nextInt(DEFAULT_TYPE.length)]);

        int numberOfProducts = random.nextInt(10) + 1;

        for(int i = 0; i < numberOfProducts; i++){
            Product product = ProductHelpers.generateInstance(codes[random.nextInt(codes.length)], i + 1);
            if(product instanceof PhysicalProduct){
                cart.setTotalWeight(cart.getTotalWeight() + ((PhysicalProduct)product).getWeight());
            }
            cart.setQuantityOfProducts(cart.getQuantityOfProducts() + 1);
            cart.getCarts().add(product);
            carts.add(product);
        }

        return cart;
    }

//    public static List<Product> getAllProductInCart(){
//
//    }
}