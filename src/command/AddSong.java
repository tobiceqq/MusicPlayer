package command;

import playlist.PlaylistManager;
import utils.InputValidator;

import java.util.Scanner;

/**
 * Adds a new song to the currently selected playlist.
 *
 * Requires the user to input the title, artist and duration.
 * Validates the input and updates the playlist accordingly.
 */
public class AddSong implements Command {

    private final PlaylistManager playlistManager;
    private final Scanner scanner;

    public AddSong(PlaylistManager playlistManager, Scanner scanner) {
        this.playlistManager = playlistManager;
        this.scanner = scanner;
    }

    /**
     * Executes the command to add a song to the current playlist.
     *
     * @return result message indicating success or failure
     */
    @Override
    public String execute() {
        if (!playlistManager.hasCurrentPlaylist()) {
            return "❌ No playlist selected. Use 'select' or 'create' to choose a playlist first.";
        }

        System.out.println("\uD83C\uDFB5 Enter song title: ");
        String title = scanner.nextLine().trim();
        if (InputValidator.isNullOrEmpty(title)) {
            return "⚠\uFE0F Title cannot be empty.";
        }

        System.out.println("\uD83D\uDC64 Enter artist name: ");
        String artist = scanner.nextLine().trim();
        if (InputValidator.isNullOrEmpty(artist)) {
            return "⚠\uFE0F Artist name cannot be empty.";
        }

        System.out.println("⏱\uFE0F Enter duration in seconds: ");
        String durationInput = scanner.nextLine().trim();
        if (InputValidator.isValidInteger(durationInput)) {
            return "⚠\uFE0F Invalid duration.";
        }

        int duration = Integer.parseInt(durationInput);
        Song newSong = new Song(title, artist, duration);
        playlistManager.getCurrentPlaylist().addSong(newSong);

        return "✅ Song added: " + newSong;
    }

    /**
     * Indicates whether this command should cause the program to exit.
     *
     * @return false, as this commands does not terminate the program
     */
    @Override
    public boolean exit() {
        return false;
    }
}
