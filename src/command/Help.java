package command;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Command that displays the help info by reading it from a file.
 */
public class Help implements Command {

    private static final String HELP_FILE_PATH = "/files/help.txt";


    /**
     * Executes the help command by reading the help file content.
     *
     * @return a string containing the help info
     */
    @Override
    public String execute() {
        try {
            Path path = Paths.get(HELP_FILE_PATH);
            return Files.readString(path);
        } catch (IOException e) {
            return "\uD83D\uDEAB Help information is currently unavailable.";
        }
    }

    /**
     * Indicates that the app should continue running.
     *
     * @return false, as help does not terminate the app
     */
    @Override
    public boolean exit() {
        return false;
    }
}
