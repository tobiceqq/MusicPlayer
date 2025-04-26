package exceptions;

/**
 * Exception thrown when the user enters an invalid or unknown command.
 */
public class InvalidCommandException extends Exception {

    public InvalidCommandException() {
        super();
    }

    public InvalidCommandException(String message) {
        super(message);
    }
}
