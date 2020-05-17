package pl.javarun.mywebshop.exception;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 16.05.2020 22:28
 * *
 * @className: AddressNotExistException
 * *
 * *
 ******************************************************/
public class AddressNotExistException extends RuntimeException {
    public AddressNotExistException(String message) {
        super(message);
    }
}
