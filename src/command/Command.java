package command;

/**
 * Represents a command that can be executed by the music player.
 * Each command performs a specific action (e.g., play, pause, next song, ...)
 */
public interface Command {

    /**
     * Executes the command.
     *
     * @return a message describing the result of the command
     */
    String execute();

    /**
     * Determines whether the command should exit the app.
     *
     * @return true if the command should terminate the program, false otherwise
     */
    boolean exit();
}
