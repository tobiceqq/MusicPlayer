package command;

import model.PlayMode;
import playlist.PlaylistManager;

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
            return "❌ No playlist selected.";
        }

        playlistManager.getCurrentPlaylist().setPlayMode(PlayMode.NORMAL);
        return "▶\uFE0F Play mode set to NORMAL.";
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
