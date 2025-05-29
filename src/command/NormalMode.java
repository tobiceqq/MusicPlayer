package command;

import model.PlayMode;
import playlist.PlaylistManager;
import utils.ConsoleStyle;

public class NormalMode implements Command {

    private final PlaylistManager playlistManager;

    public NormalMode(PlaylistManager playlistManager) {
        this.playlistManager = playlistManager;
    }

    /**
     * Executes the command to switch to NORMAL play mode.
     *
     * @return message confirming the mode switch or an error
     */
    @Override
    public String execute() {
        if (!playlistManager.hasCurrentPlaylist()) {
            return ConsoleStyle.color("❌ No playlist selected." , ConsoleStyle.RED);
        }

        playlistManager.getCurrentPlaylist().setPlayMode(PlayMode.NORMAL);
        return ConsoleStyle.color("▶\uFE0F Play mode set to " + ConsoleStyle.bold("normal") , ConsoleStyle.GREEN);
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
