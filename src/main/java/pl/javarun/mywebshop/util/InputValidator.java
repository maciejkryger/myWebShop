package pl.javarun.mywebshop.util;


public class InputValidator {


    public static Boolean nameValidator(String name) {
        char[] letters = name.toCharArray();
        char[] wrongCharTable = {'"', ':', ';', '!', '?', '+', '-', ',', '#', '$', '%', '^', '*', '(', ')', '=', '<', '>', '/', '~',
                '`', '{', '}', '|'};
        for (char letter : letters) {
            for (char c : wrongCharTable) {
                if (letter == c) {
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
        for (char letter : letters) {
            for (char c : wrongCharTable) {
                if (letter == c) {
                    return false;
                }
                if (letters.length < 8) {
                    return false;
                }
            }
        }
        return true;
    }


    public static Boolean emailValidator(String email) {
        char[] letters = email.toCharArray();
        char[] wrongCharTable = {'"', ':', ';', '!', '?', '+', '-', ',', '#', '$', '%', '^', '*', '(', ')', '=', '<', '>', '/', '~',
                '`', '{', '}', '|', ' '};
        int pointCounter = 0;
        int emailCharCounter = 0;
        for (char letter : letters) {
            for (char c : wrongCharTable) {
                if (letter == c) {
                    return false;
                }
            }
            if (letter == '.') {
                pointCounter++;
            }
            if (letter == '@') {
                emailCharCounter++;
            }
        }
        if (emailCharCounter != 1 || pointCounter < 1 || letters.length < 6) {
            return false;
        }
        return true;
    }

    public static Boolean phoneValidator(String phone) {
        char[] letters = phone.toCharArray();
        char[] wrongCharTable = {'"', ':', ';', '!', '?', '+', '-', ',', '#', '$', '%', '^', '*', '(', ')', '=', '<', '>', '/', '~',
                '`', '{', '}', '|', 'A', 'a', 'Ą', 'ą', 'B', 'b', 'C', 'c', 'Ć', 'ć', 'D', 'd', 'E', 'e', 'Ę', 'ę', 'F', 'f', 'G', 'g',
                'H', 'h', 'I', 'i', 'J', 'j', 'K', 'k', 'L', 'l', 'Ł', 'ł', 'M', 'm', 'N', 'n', 'Ń', 'ń', 'O', 'o', 'Ó', 'ó', 'P', 'p', 'R', 'r',
                'S', 's', 'Ś', 'ś', 'T', 't', 'U', 'u', 'V', 'v', 'W', 'w', 'Q', 'q', 'X', 'x', 'Y', 'y', 'Z', 'z', 'Ż', 'ż', 'Ź', 'ź'};
        if (letters.length != 9) {
            return false;
        }
        for (char letter : letters) {
            for (char c : wrongCharTable) {
                if (letter == c) {
                    return false;
                }
            }
        }
        return true;
    }

    public static Boolean postCodeValidator(String postCode) {
        char[] letters = postCode.toCharArray();
        char[] wrongCharTable = {'"', ':', ';', '!', '?', '+', ',', '#', '$', '%', '^', '*', '(', ')', '=', '<', '>', '/', '~',
                '`', '{', '}', '|', ' ', 'A', 'a', 'Ą', 'ą', 'B', 'b', 'C', 'c', 'Ć', 'ć', 'D', 'd', 'E', 'e', 'Ę', 'ę', 'F', 'f', 'G', 'g',
                'H', 'h', 'I', 'i', 'J', 'j', 'K', 'k', 'L', 'l', 'Ł', 'ł', 'M', 'm', 'N', 'n', 'Ń', 'ń', 'O', 'o', 'Ó', 'ó', 'P', 'p', 'R', 'r',
                'S', 's', 'Ś', 'ś', 'T', 't', 'U', 'u', 'V', 'v', 'W', 'w', 'Q', 'q', 'X', 'x', 'Y', 'y', 'Z', 'z', 'Ż', 'ż', 'Ź', 'ź'};
        if (letters.length != 6) {
            return false;
        }
        for (char letter : letters) {
            for (char c : wrongCharTable) {
                if (letter == c) {
                    return false;
                }
                if (letters[2] != '-') {
                    return false;
                }
            }
        }
        return true;
    }

    public static Boolean houseNoValidator(String name) {
        char[] letters = name.toCharArray();
        char[] wrongCharTable = {'"', ':', ';', '!', '?', '+', '-', ',', '#', '$', '%', '^', '*', '(', ')', '=', '<', '>', '/', '~',
                '`', '{', '}', '|'};
        if (letters.length > 4) {
            return false;
        }

        for (char letter : letters) {
            for (char c : wrongCharTable) {
                if (letter == c) {
                    return false;
                }
            }
        }
        return true;
    }
}
