package command;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Command that displays the help info by reading it from a file.
 */
public class Help implements Command {


    /**
     * Executes the help command by reading the help file content.
     *
     * @return a string containing the help info
     */
    @Override
    public String execute() {
        try {
            Path path = Paths.get("src/files/help.txt");
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
