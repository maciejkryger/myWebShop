package pl.javarun.mywebshop.util;


public class InputValidator {


    public static Boolean nameValidator(String name) {
        char[] letters = name.toCharArray();
        char[] wrongCharTable = {'"', ':', ';', '!', '?', '+', '-', ',', '#', '$', '%', '^', '*', '(', ')', '=', '<', '>', '/', '~',
                '`', '{', '}', '|'};
        for (int i = 0; i < letters.length; i++) {
            for (int j = 0; j < wrongCharTable.length; j++) {
                if (letters[i] == wrongCharTable[j]) {
                    return false;
                }
                if (letters.length < 3) {
                    return false;
                }
            }
        }
        return true;
    }

    public static Boolean passwordValidator(String name) {
        char[] letters = name.toCharArray();
        char[] wrongCharTable = {'"', ':', ';', '+', '-', ',', '#', '^', '*', '(', ')', '=', '<', '>', '/', '~',
                '`', '{', '}', '|'};
        for (int i = 0; i < letters.length; i++) {
            for (int j = 0; j < wrongCharTable.length; j++) {
                if (letters[i] == wrongCharTable[j]) {
                    return false;
                }
                if (letters.length < 9) {
                    return false;
                }
            }
        }
        return true;
    }


    public static Boolean emailValidator(String email) {
        char[] letters = email.toCharArray();
        char[] wrongCharTable = {'"', ':', ';', '!', '?', '+', '-', ',', '#', '$', '%', '^', '*', '(', ')', '=', '<', '>', '/', '~',
                '`', '{', '}', '|',' '};
        int pointCounter = 0;
        int emailCharCounter = 0;
        for (int i = 0; i < letters.length; i++) {
            for (int j = 0; j < wrongCharTable.length; j++) {
                if (letters[i] == wrongCharTable[j]) {
                    System.out.println("wrong char");
                    return false;
                }
            }
            if (letters[i] == '.') {
                pointCounter++;
            }
            if (letters[i] == '@') {
                emailCharCounter++;
            }
        }
        if (emailCharCounter != 1  || pointCounter < 1 || letters.length < 6) {
            System.out.println("counter if");
            return false;
        }
        return true;
    }
}
