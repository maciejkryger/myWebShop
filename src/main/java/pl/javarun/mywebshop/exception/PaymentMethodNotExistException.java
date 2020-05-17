package pl.javarun.mywebshop.exception;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 17.05.2020 09:58
 * *
 * @className: PaymentMethodNotExistException
 * *
 * *
 ******************************************************/
public class PaymentMethodNotExistException extends RuntimeException {
    public PaymentMethodNotExistException(String message) {
        super(message);
    }
}
