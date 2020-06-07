package pl.javarun.mywebshop.exception;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 06.06.2020 22:23
 * *
 * @className: PromoCodeNotExistException
 * *
 * *
 ******************************************************/
public class PromoCodeNotExistException extends RuntimeException{
    public PromoCodeNotExistException(String message) {
        super(message);
    }
}
