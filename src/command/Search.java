package command;

import model.Song;
import playlist.PlaylistManager;
import utils.ConsoleStyle;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;

/**
 * Searches for songs in the current playlist using a regex pattern.
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
            return ConsoleStyle.color("❌ No playlist selected." , ConsoleStyle.RED);
        }

        List<Song> songs = playlistManager.getCurrentPlaylist().getSongs();
        if (songs.isEmpty()) {
            return ConsoleStyle.color("\uD83D\uDCED Playlist is empty.", ConsoleStyle.BLUE);
        }

        System.out.println(ConsoleStyle.bold("\uD83D\uDD0E Enter search term: "));
        String patternInput = scanner.nextLine().trim();

        Pattern pattern;
        try {
            pattern = Pattern.compile(patternInput, Pattern.CASE_INSENSITIVE);
        } catch (PatternSyntaxException e) {
            return ConsoleStyle.color("⚠\uFE0F Invalid regex pattern" , ConsoleStyle.YELLOW);
        }

        List<Song> matches = songs.stream().filter(song -> pattern.matcher(song.getTitle()).find() ||
                pattern.matcher(song.getArtist()).find()).collect(Collectors.toList());
        if (matches.isEmpty()) {
            return ConsoleStyle.color("\uD83D\uDD0D Not found." , ConsoleStyle.CYAN);
        }

        StringBuilder result = new StringBuilder(ConsoleStyle.color("✅ Found songs:\n" , ConsoleStyle.GREEN));
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
