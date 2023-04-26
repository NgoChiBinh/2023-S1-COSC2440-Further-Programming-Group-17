package org.example.libs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.UUID;

public class Helpers {
    public static int convertBooleanToNumber(boolean bool) {
        return bool ? 1 : 0;
    }

    public static Path getFilePathHelper(String folder, String file){
        return Paths.get(System.getProperty("user.dir"), "src", folder, "resources", file);
    }

    public static double doubleFormatter(double val){
        return Double.parseDouble(new DecimalFormat("#.##").format(val));
    }

    public static void createFile(String folder, String fileName){
        Path of = Helpers.getFilePathHelper(folder, fileName);

        if(!Files.exists(of)){
            try{
                Files.createFile(of);
                System.out.println("Create file successfully");
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public static String generateCouponCode() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}
