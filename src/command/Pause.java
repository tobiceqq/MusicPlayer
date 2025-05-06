package command;

import audio.AudioPlayer;
import playlist.PlaylistManager;

public class Pause implements Command {

    private final PlaylistManager playlistManager;
    private final AudioPlayer audioPlayer;

    public Pause(PlaylistManager playlistManager, AudioPlayer audioPlayer) {
        this.playlistManager = playlistManager;
        this.audioPlayer = audioPlayer;
    }

    /**
     * Executes the pause command.
     *
     * @return a status message indicating whether playback was paused
     */
    @Override
    public String execute() {
        if (!playlistManager.hasCurrentPlaylist()) {
            return "❌ No playlist selected.";
        }

        return audioPlayer.pause();
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
