package pl.javarun.mywebshop.exception;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 16.04.2020 22:28
 * *
 * @className: AddressNotExistException
 * *
 * *
 ******************************************************/
public class ColorPerMaterialNotExistException extends RuntimeException {
    public ColorPerMaterialNotExistException(String message) {
        super(message);
    }
}
