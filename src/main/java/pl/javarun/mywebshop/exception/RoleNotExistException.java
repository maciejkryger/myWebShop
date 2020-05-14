package pl.javarun.mywebshop.exception;

public class RoleNotExistException extends RuntimeException {
    public RoleNotExistException(String message) {
        super(message);
    }
}
