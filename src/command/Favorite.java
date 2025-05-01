package command;

import model.Song;
import playlist.PlaylistManager;

import java.util.Scanner;

/**
 * Toggles the favorite status of a song in the current playlist.
 * If the song is already in favorite, it will be unmarked.
 */
public class Favorite implements Command {

    private final PlaylistManager playlistManager;
    private final Scanner scanner;

    public Favorite(PlaylistManager playlistManager, Scanner scanner) {
        this.playlistManager = playlistManager;
        this.scanner = scanner;
    }

    /**
     * Executes the command to mark/unmark a song as favorite.
     *
     * @return result message with the outcome
     */
    @Override
    public String execute() {
        if (!playlistManager.hasCurrentPlaylist()) {
            return "❌ No playlist selected.";
        }

        if (playlistManager.getCurrentPlaylist().getSongs().isEmpty()) {
            return "\uD83D\uDCED Playlist is empty.";
        }

        System.out.println("⭐ Enter the title of the song to (un)favorite: ");
        String title = scanner.nextLine().trim();

        Song song = playlistManager.getCurrentPlaylist().findSongByTitle(title);
        if (song == null) {
            return "⚠\uFE0F Song not found in current playlist.";
        }

        song.setFavorite(!song.isFavorite());
        return song.isFavorite()
                ? "✅ '" + song.getTitle() + "' marked as favorite."
                : "❎ '" + song.getTitle() + "' removed from favorites.";
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
