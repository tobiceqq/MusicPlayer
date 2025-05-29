package command;

import playlist.PlaylistManager;
import utils.ConsoleStyle;

import java.util.Scanner;

/**
 * Command to create a new playlist.
 */
public class CreatePlaylist implements Command {

    private final PlaylistManager playlistManager;
    private final Scanner scanner;

    public CreatePlaylist(PlaylistManager playlistManager, Scanner scanner) {
        this.playlistManager = playlistManager;
        this.scanner = scanner;
    }

    /**
     * Prompts user to enter playlist name and artist, the creates a playlist.
     *
     * @return confirmation message or error
     */
    @Override
    public String execute() {
        System.out.println("\uD83C\uDFB5 Enter the name of the new playlist: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            return ConsoleStyle.color("❌ Playlist name can't be empty." , ConsoleStyle.RED);
        }

        System.out.println("\uD83C\uDFA4 Enter artist name (optional): ");
        String artist = scanner.nextLine().trim();

        if (playlistManager.getPlaylist(name) != null) {
            return ConsoleStyle.color("⚠\uFE0F Playlist with this name already exists." , ConsoleStyle.YELLOW);
        }

        playlistManager.createPlaylist(name, artist);
        return ConsoleStyle.color("✅ Playlist \"" + name + "\" was successfully created!" , ConsoleStyle.GREEN);
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
