package pl.javarun.mywebshop.exception;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 24.02.2020 19:38
 * *
 * @className: FasteningTypeNotExistException
 * *
 * *
 ******************************************************/
public class FasteningTypeNotExistException extends RuntimeException {
    public FasteningTypeNotExistException(String message) {
        super(message);
    }
}
