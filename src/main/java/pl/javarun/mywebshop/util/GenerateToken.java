package pl.javarun.mywebshop.util;

import java.util.Random;

public class GenerateToken {

    public static String startGenerateToken(){

        Random random = new Random();
        String token="";
        for (int i=0; i<10; i++){
            token+=random.nextInt(10);
            token+=String.valueOf(random.nextInt(10));
        }
        return token;
    }
}
