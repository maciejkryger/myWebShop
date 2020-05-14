package pl.javarun.mywebshop.exception;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 24.02.2020 19:33
 * *
 * @className: MaterialNotExistException
 * *
 * *
 ******************************************************/
public class MaterialNotExistException extends RuntimeException {
    public MaterialNotExistException(String message) {
        super(message);
    }
}
