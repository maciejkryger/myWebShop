package pl.javarun.mywebshop.exception;

public class ColorPerMaterialNotExistException extends RuntimeException {
    public ColorPerMaterialNotExistException(String message) {
        super(message);
    }
}
