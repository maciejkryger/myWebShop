package pl.javarun.mywebshop.exception;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 12.05.2020 19:33
 * *
 * @className: OrderItemNotExistException
 * *
 * *
 ******************************************************/
public class OrderItemNotExistException extends RuntimeException {
    public OrderItemNotExistException(String message) {
        super(message);
    }
}
