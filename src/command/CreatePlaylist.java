package command;

import playlist.PlaylistManager;

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
            return "❌ Playlist name can't be empty.";
        }

        System.out.println("\uD83C\uDFA4 Enter artist name (optional): ");
        String artist = scanner.nextLine().trim();

        if (playlistManager.getPlaylist(name) != null) {
            return "⚠\uFE0F Playlist with this name already exists.";
        }

        playlistManager.createPlaylist(name, artist);
        return "✅ Playlist \"" + name + "\" was successfully created!";
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
