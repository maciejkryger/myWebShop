package pl.javarun.mywebshop.exception;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 12.05.2020 19:33
 * *
 * @className: OrderNotExistException
 * *
 * *
 ******************************************************/
public class OrderNotExistException extends RuntimeException {
    public OrderNotExistException(String message) {
        super(message);
    }
}
