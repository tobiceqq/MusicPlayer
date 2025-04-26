package exceptions;

/**
 * Exception thrown when a song is not found.
 */
public class SongNotFoundException extends Exception {

    public SongNotFoundException() {
        super();
    }

    /**
     * Constructs a new SongNotFoundException with a specified detail message.
     *
     * @param message the detail message
     */
    public SongNotFoundException(String message) {
        super(message);
    }
}
