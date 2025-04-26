package exceptions;

/**
 * Exception thrown when a playlist is not found.
 */
public class PlaylistNotFoundException extends Exception {

    public PlaylistNotFoundException() {
        super();
    }

    /**
     * Constructs a new PlaylistNotFoundException with a specified detail message.
     *
     * @param message the detail message
     */
    public PlaylistNotFoundException(String message) {
        super(message);
    }
}
