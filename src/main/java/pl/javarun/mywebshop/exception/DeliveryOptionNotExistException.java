package pl.javarun.mywebshop.exception;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 16.05.2020 20:06
 * *
 * @className: DeliveryOptionNotExistException
 * *
 * *
 ******************************************************/
public class DeliveryOptionNotExistException extends RuntimeException {
    public DeliveryOptionNotExistException(String message) {
        super(message);
    }
}
