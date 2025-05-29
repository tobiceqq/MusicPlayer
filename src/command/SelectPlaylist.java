package command;

import model.Playlist;
import playlist.PlaylistManager;
import utils.ConsoleStyle;

import java.util.Scanner;

/**
 * Allows user to select a playlist by its name.
 */
public class SelectPlaylist implements Command {

    private final PlaylistManager playlistManager;
    private final Scanner scanner;

    public SelectPlaylist(PlaylistManager playlistManager, Scanner scanner) {
        this.playlistManager = playlistManager;
        this.scanner = scanner;
    }

    /**
     * Executes the command to select a playlist.
     *
     *
     * @return a result message indicating success or failure
     */
    @Override
    public String execute() {
        System.out.println("\uD83C\uDFB5 " + ConsoleStyle.bold("Enter the name of the playlist to select: "));
        String input = scanner.nextLine().trim();

        boolean success = playlistManager.selectPlaylist(input);
        if (success) {
            return ConsoleStyle.color("✅ Playlist " , ConsoleStyle.GREEN) + ConsoleStyle.bold(input) + ConsoleStyle.color(" selected." , ConsoleStyle.GREEN);
        } else {
            return ConsoleStyle.color("❌ Playlist " , ConsoleStyle.RED) + ConsoleStyle.bold(input) + ConsoleStyle.color(" not found." , ConsoleStyle.RED);
        }
    }

    /**
     * Indicates whether this command should cause the program to exit.
     *
     * @return false, as this command does not terminate the program
     */
    @Override
    public boolean exit() {
        return false;
    }
}
