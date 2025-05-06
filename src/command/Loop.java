package command;

import model.PlayMode;
import playlist.PlaylistManager;

import java.util.Scanner;

/**
 * Sets the play mode to loop the current song or the entire playlist.
 *
 * User can choose between LOOP_ONE or LOOP_ALL.
 */
public class Loop implements Command {

    private final PlaylistManager playlistManager;
    private final Scanner scanner;

    public Loop(PlaylistManager playlistManager, Scanner scanner) {
        this.playlistManager = playlistManager;
        this.scanner = scanner;
    }

    /**
     * Executes the loop command to set the loop mode.
     *
     * @return result message indicating the new play mode
     */
    @Override
    public String execute() {
        if (!playlistManager.hasCurrentPlaylist()) {
            return "❌ No playlist selected.";
        }

        System.out.println("\uD83D\uDD01 Type 'one' to loop current song or 'all' to loop the entire playlist: ");
        String input = scanner.nextLine().trim().toLowerCase();

        switch (input) {
            case "one":
                playlistManager.getCurrentPlaylist().setPlayMode(PlayMode.LOOP_ONE);
                return "\uD83D\uDD01 Loop mode set to repeat current song.";
            case "all":
                playlistManager.getCurrentPlaylist().setPlayMode(PlayMode.LOOP_ALL);
                return "\uD83D\uDD01 Loop mode set to repeat entire playlist.";
            default:
                return "⚠\uFE0F Invalid input. Type 'one' or 'all'.";
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
