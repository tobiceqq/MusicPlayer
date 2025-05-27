package command;

import model.Playlist;
import playlist.PlaylistManager;

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
        System.out.println("\uD83C\uDFB5 Enter the name of the playlist to select: ");
        String input = scanner.nextLine().trim();

        boolean success = playlistManager.selectPlaylist(input);
        if (success) {
            return "✅ Playlist '" + input + "' selected.";
        } else {
            return "❌ Playlist '" + input + "' not found.";
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
