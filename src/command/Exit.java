package command;

/**
 * Command that exits the music player app.
 */
public class Exit implements Command {

    /**
     * Executes the exit command.
     *
     * @return a message indicating that the music player is closing
     */
    @Override
    public String execute() {
        return "\uD83D\uDC4B Exiting the music player...";
    }

    /**
     * Indicates that the app should terminate.
     *
     * @return true, as this command exits the app
     */
    @Override
    public boolean exit() {
        return true;
    }
}
