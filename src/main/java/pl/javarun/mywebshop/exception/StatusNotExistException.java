package pl.javarun.mywebshop.exception;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 30.05.2020 21:28
 * *
 * @className: StatusNotExistException
 * *
 * *
 ******************************************************/
public class StatusNotExistException extends RuntimeException {
    public StatusNotExistException(String message) {
        super(message);
    }
}
