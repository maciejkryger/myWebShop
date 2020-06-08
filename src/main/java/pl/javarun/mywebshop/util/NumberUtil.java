package pl.javarun.mywebshop.util;

public class NumberUtil {

    /**
     * funkcja zaokrągla podaną liczbę do określonej liczby miejsc po przecinku
     * @param num double - liczba do zaokrąglenia
     * @param dec int - liczba miejsc po przecinku;
     * @return double - zwraca zaokrągloną liczbę
     */
    public static double roundToDecimal(double num, int dec) {
        int multi = (int) Math.pow(10, dec);
        int temp = (int) Math.round(num * multi);
        return (double) temp / multi;
    }
}
