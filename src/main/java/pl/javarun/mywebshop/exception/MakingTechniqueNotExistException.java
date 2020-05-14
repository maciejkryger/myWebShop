package pl.javarun.mywebshop.exception;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 24.02.2020 18:10
 * *
 * @className: MakingTechniqueNotExistException
 * *
 * *
 ******************************************************/
public class MakingTechniqueNotExistException extends RuntimeException {
    public MakingTechniqueNotExistException(String message) {
        super(message);
    }
}
