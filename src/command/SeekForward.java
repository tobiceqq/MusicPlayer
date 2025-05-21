package command;

import audio.AudioPlayer;
import playlist.PlaylistManager;

/**
 * Fast-forwards the current song by a given number of seconds.
 */
public class SeekForward implements Command {

    private final PlaylistManager playlistManager;
    private final AudioPlayer audioPlayer;
    private final long secondsToSeek;

    public SeekForward(PlaylistManager playlistManager, AudioPlayer audioPlayer, long secondsToSeek) {
        this.playlistManager = playlistManager;
        this.audioPlayer = audioPlayer;
        this.secondsToSeek = secondsToSeek;
    }

    /**
     * Executes the seek forward command.
     *
     * @return message indicating the result of the forward seek.
     */
    public String execute() {
        if (!playlistManager.hasCurrentPlaylist()) {
            return "❌ No playlist selected.";
        }

        return audioPlayer.seekForward(secondsToSeek);
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
