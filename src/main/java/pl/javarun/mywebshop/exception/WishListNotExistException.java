package pl.javarun.mywebshop.exception;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 27.05.2020 20:5
 * *
 * @className: WishListNotExistException
 * *
 * *
 ******************************************************/
public class WishListNotExistException extends RuntimeException{
    public WishListNotExistException(String message) {
        super(message);
    }
}
