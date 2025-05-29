package command;

import model.PlayMode;
import playlist.PlaylistManager;
import utils.ConsoleStyle;

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
            return ConsoleStyle.color("❌ No playlist selected." , ConsoleStyle.RED);
        }

        System.out.println("\uD83D\uDD01 "  + ConsoleStyle.bold("Type ") + ConsoleStyle.underline("one") + ConsoleStyle.bold(" to loop current song or ") + ConsoleStyle.underline("all") + ConsoleStyle.bold(" to loop the entire playlist: "));
        String input = scanner.nextLine().trim().toLowerCase();

        switch (input) {
            case "one":
                playlistManager.getCurrentPlaylist().setPlayMode(PlayMode.LOOP_ONE);
                return ConsoleStyle.color("\uD83D\uDD01 Loop mode set to repeat " + ConsoleStyle.bold("current song") + "." , ConsoleStyle.GREEN);
            case "all":
                playlistManager.getCurrentPlaylist().setPlayMode(PlayMode.LOOP_ALL);
                return ConsoleStyle.color("\uD83D\uDD01 Loop mode set to repeat " + ConsoleStyle.bold("entire playlist") + "." , ConsoleStyle.GREEN);

            default:
                return ConsoleStyle.color("⚠\uFE0F Invalid input. Type " + ConsoleStyle.underline("one") + " or " + ConsoleStyle.underline("all") + "." , ConsoleStyle.YELLOW);
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
