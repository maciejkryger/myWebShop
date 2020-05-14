package pl.javarun.mywebshop.exception;

public class RuleNotExistException extends RuntimeException {
    public RuleNotExistException(String message) {
        super(message);
    }
}
