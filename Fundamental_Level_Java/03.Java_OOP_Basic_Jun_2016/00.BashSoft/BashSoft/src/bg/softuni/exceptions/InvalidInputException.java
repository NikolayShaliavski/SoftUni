package bg.softuni.exceptions;

public class InvalidInputException extends RuntimeException {

    private static final String INVALID_COMMAND_EXCEPTION = "The command %s is invalid!";

    public InvalidInputException(String message) {
        super(String.format(INVALID_COMMAND_EXCEPTION, message));
    }
}
