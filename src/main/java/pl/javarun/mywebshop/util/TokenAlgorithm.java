package pl.javarun.mywebshop.util;


import java.util.Random;

public class TokenAlgorithm {

    public static String generate() {

        int leftLimitDigitInt = 48; // digit '0'
        int rightLimitDigitInt = 57; // digit '9'
        int leftLimitSmallCharInt = 97; // letter 'a'
        int rightLimitSmallCharInt = 122; // letter 'z'
        int leftLimitBigCharInt = 65; // letter 'A'
        int rightLimitBigCharInt = 90; // letter 'Z'
        int targetStringLength = 20;
        Random random = new Random();
        int randomInt = 0;
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int charTypeInt = random.nextInt(3) + 1;
            if (charTypeInt == 1) {
                randomInt = leftLimitDigitInt + (int)
                        (random.nextFloat() * (rightLimitDigitInt - leftLimitDigitInt + 1));
            }
            if (charTypeInt == 2) {
                randomInt = leftLimitSmallCharInt + (int)
                        (random.nextFloat() * (rightLimitSmallCharInt - leftLimitSmallCharInt + 1));
            }
            if (charTypeInt == 3) {
                randomInt = leftLimitBigCharInt + (int)
                        (random.nextFloat() * (rightLimitBigCharInt - leftLimitBigCharInt + 1));
            }
            buffer.append((char) randomInt);
        }
        String token = buffer.toString();
        return token;
    }
}