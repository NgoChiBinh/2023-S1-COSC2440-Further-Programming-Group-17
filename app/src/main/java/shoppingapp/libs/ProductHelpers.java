package org.example.libs;

import org.example.product.DigitalProduct;
import org.example.product.PhysicalProduct;
import org.example.product.Product;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ProductHelpers {
    public enum TYPE{
        PHYSICAL,
        DIGITAL
    }
//    private final static String PRODUCT_URI = "products.txt";

    private static List<Product> products = new ArrayList<>();
    private static Random random = new Random();

    public static String serializeHelper(Product product){
        String formatter = null;
        if(product instanceof PhysicalProduct physicalProduct){
            formatter = String.format("%s,%s,%d,%.2f,%.2f,%s,%d",
                    physicalProduct.getName(),
                    physicalProduct.getDescription(),
                    physicalProduct.getQuantity(),
                    physicalProduct.getPrice(),
                    physicalProduct.getWeight(),
                    physicalProduct.getType(),
                    Helpers.convertBooleanToNumber(physicalProduct.isGift())
                    );
        }else if(product instanceof DigitalProduct digitalProduct){
            formatter = String.format("%s,%s,%d,%.2f,%s,%d",
                    digitalProduct.getName(),
                    digitalProduct.getDescription(),
                    digitalProduct.getQuantity(),
                    digitalProduct.getPrice(),
                    digitalProduct.getType(),
                    Helpers.convertBooleanToNumber(digitalProduct.isGift())
            );
        }

        return formatter;
    }

    public static boolean addProduct(Path path, Product product) throws IOException {
        String serialization = serializeHelper(product);

        try(Stream<String> stream = Files.lines(path)){
            if(stream.anyMatch(line -> line.startsWith(product.getName()))){
                System.err.println("\nThe given product has already exist in the current stock.");
                return false;
            }
        }

        Files.write(path, Collections.singleton(serialization), StandardOpenOption.APPEND);

        return true;
    }

    public static void generateAllInstances(char code, int numberOfInstances, Path path) throws IOException {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < numberOfInstances; i++){
            sb.append(serializeHelper(generateInstance(code, i + 1))).append("\n");
        }

        String fileName = String.valueOf(path.getFileName());
        String folder = path.getParent().getParent().getFileName().toString();

        Helpers.createFile(folder, fileName);

        if(Files.readAllLines(path).size() == 0){
            Files.write(path, Collections.singletonList(sb.toString().trim()), StandardOpenOption.APPEND);
        }
        else{
            System.err.println("Your 50 available products are initially created");
        }
    }

    public static Product generateInstance(char code, int id){
        Product product = null;
        String name = String.valueOf(code) + id;
        String description = "This is " + (random.nextBoolean() ? "a" : "an") + " " +  (random.nextBoolean() ? "awesome" : "great") + " product";
        int quantity = random.nextInt(10) + 1;
        double price = Helpers.doubleFormatter(random.nextDouble() * 100);
        boolean isGift = random.nextBoolean();

        if(code == 'P'){
            // Generate physical product
            double weight = Helpers.doubleFormatter(random.nextDouble() * 25);
            product = new PhysicalProduct(name, description, quantity, price, weight, isGift);
        }
        else{
            product = new DigitalProduct(name, description, quantity, price, isGift);
        }

        return product;
    }

    public static List<Product> getProducts(Path path) throws IOException{
        products = Files.lines(path, StandardCharsets.UTF_8)
                .map(line -> {
                    System.out.println(line);
                    String[] pattern = line.split(",");
                    String name = pattern[0];
                    String description = pattern[1];
                    int quantity = Integer.parseInt(pattern[2]);
                    double price = Double.parseDouble(pattern[3]);
                    String type = pattern[5];
                    boolean isGift = (pattern.length >= pattern.length + 1 && Integer.parseInt(pattern[6]) == 1);
                    Product product = null;

                    try{
                        if(type.equals(TYPE.PHYSICAL.toString())){
                            Double weight = Double.parseDouble(pattern[4]);
                            product = new PhysicalProduct(name, description, quantity, price, weight, isGift);
                        }else{
                            product = new DigitalProduct(name, description, quantity, price, isGift);
                        }
                    }catch(NumberFormatException err){
                        System.err.println("Error processing lines: " + line);
                    }
                    return product;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return products;
    }

    public static Product findProductByName(Path path, String productName) throws IOException{
        for(Product product: getProducts(path)){
            if(product.getName().equals(productName)){
                if(product instanceof PhysicalProduct || product instanceof DigitalProduct)
                    return product;
            }
        }

        return null;
    }

    public static List<Product> getPhysicalProducts(Path path) throws IOException {
        List<Product> physicalProducts = new ArrayList<>();

        for(Product product : getProducts(path)){
            if(product instanceof PhysicalProduct){
                physicalProducts.add(product);
            }
        }

        return physicalProducts;
    }

    public static List<Product> getDigitalProducts(Path path) throws IOException{
        List<Product> digitalProducts = new ArrayList<>();

        for(Product product: getProducts(path)){
            if(product instanceof DigitalProduct){
                digitalProducts.add(product);
            }
        }

        return digitalProducts;
    }

    public static void displayAllProducts(Path path) throws IOException {
        for(Product product: getProducts(path)){
            if(product instanceof DigitalProduct || product instanceof PhysicalProduct){
                System.out.println(product.toString());
            }
        }
    }

    public static void updateProductByPrice(Path path, String productName, String newPrice) throws IOException {
        List<String> lines = Files.lines(path)
                .map(line -> {
                    String[] pattern = line.split(",");

                    if (pattern[0].equals(productName)) {
                        pattern[3] = newPrice;
                    }
                    return String.join(",", pattern);
                })
                .collect(Collectors.toList());

        Files.write(path, lines, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public static void updateProductByName(Path path, String oldName, String newName) throws IOException {
        boolean nameExist = Files.lines(path)
                .map(line -> line.split(",")[0])
                .anyMatch(name -> name.equals(oldName));

        if(!nameExist){
            System.err.println("Cannot update the product due to invalid old name.");
            return;
        }
        List<String> lines = Files.lines(path)
                .map(line -> {
                    String[] pattern = line.split(",");

                    if (pattern[0].equals(oldName)) {
                        pattern[0] = newName;
                    }
                    return String.join(",", pattern);
                })
                .collect(Collectors.toList());

        Files.write(path, lines, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public static void removeProductByName(Path path, String name) throws IOException{
        List<String> lines = Files.lines(path)
                .filter(line -> !line.split(",")[0].equals(name))
                .collect(Collectors.toList());

        Files.write(path, lines);

        if(lines.size() == Files.readAllLines(path).size()){
            System.err.println("Product with name " + name + " does not exist in the product catalog.");
        }
    }

    public static List<String> getLineOfProduct(Path path, String productName) throws IOException {
        List<String> lines = Files.lines(path)
                .filter(line -> line.split(",")[0].equals(productName))
                .collect(Collectors.toList());

        return lines;
    }

}
