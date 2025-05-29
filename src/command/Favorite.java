package command;

import playlist.PlaylistManager;
import model.Song;
import utils.ConsoleStyle;

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
            return ConsoleStyle.color("❌ No playlist selected." , ConsoleStyle.RED);
        }

        if (playlistManager.getCurrentPlaylist().getSongs().isEmpty()) {
            return ConsoleStyle.color("\uD83D\uDCED Playlist is empty.", ConsoleStyle.BLUE);
        }

        System.out.println("⭐ " +  ConsoleStyle.bold("Enter the title of the song to (un)favorite: "));
        String title = scanner.nextLine().trim();

        Song song = playlistManager.getCurrentPlaylist().findSongByTitle(title);
        if (song == null) {
            return ConsoleStyle.color("⚠\uFE0F Song not found in current playlist." , ConsoleStyle.YELLOW);
        }

        boolean nowFavorite = !song.isFavorite();
        song.setFavorite(nowFavorite);

        if (nowFavorite) {
            playlistManager.addFavoriteSong(song);
            return ConsoleStyle.color("✅ " + ConsoleStyle.bold(song.getTitle()) + " marked as favorite." , ConsoleStyle.GREEN);
        } else {
            playlistManager.getFavoritePlaylist().removeSong(song);
            return ConsoleStyle.color("❎ " + ConsoleStyle.bold(song.getTitle()) + " removed from favorites." , ConsoleStyle.GREEN);
        }
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
