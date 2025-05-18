package command;

import model.Song;
import playlist.PlaylistManager;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;

/**
 * Searches for songs in the current playlist usinga regex pattern.
 */
public class Search implements Command{

    private final PlaylistManager playlistManager;
    private final Scanner scanner;

    public Search(PlaylistManager playlistManager, Scanner scanner) {
        this.playlistManager = playlistManager;
        this.scanner = scanner;
    }

    /**
     * Executes the search command.
     *
     * @return message with matching songs or warning
     */
    public String execute() {
        if (!playlistManager.hasCurrentPlaylist()) {
            return "❌ No playlist selected.";
        }

        List<Song> songs = playlistManager.getCurrentPlaylist().getSongs();
        if (songs.isEmpty()) {
            return "\uD83D\uDCED Playlist is empty.";
        }

        System.out.println("\uD83D\uDD0E Enter search term: ");
        String patternInput = scanner.nextLine().trim();

        Pattern pattern;
        try {
            pattern = Pattern.compile(patternInput, Pattern.CASE_INSENSITIVE);
        } catch (PatternSyntaxException e) {
            return "⚠\uFE0F Invalid regex pattern";
        }

        List<Song> matches = songs.stream().filter(song -> pattern.matcher(song.getTitle()).find() ||
                pattern.matcher(song.getArtist()).find()).collect(Collectors.toList());
        if (matches.isEmpty()) {
            return "\uD83D\uDD0D Not found.";
        }

        StringBuilder result = new StringBuilder("✅ Found songs:\n");
        for (Song song : matches) {
            result.append("• ").append(song).append("\n");
        }
        return result.toString().trim();
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
