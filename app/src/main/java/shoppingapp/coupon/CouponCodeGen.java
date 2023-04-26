package shoppingapp.coupon;

import shoppingapp.product.Product;

public class CouponCodeGen {

    private static char[] productCode;

    private static int codeLength = 4;

    private static String codeString = "";

    public static String genCode(Product product) 
    {
        productCode = product.getName().
            replaceAll("\\s", "").
            toUpperCase().
            toCharArray();

        // System.out.println(productCode);
        if (productCode[0] == 'P') {
            StringBuilder sb = new StringBuilder(codeString + productCode[0]);

            sb.append(productCode, 9, codeLength);
    
            return sb.toString();
        } else {
            StringBuilder sb = new StringBuilder(codeString + productCode[0]);

            sb.append(productCode, 8, codeLength);
    
            return sb.toString();
        }

    }

}
