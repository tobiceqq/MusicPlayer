package command;

import audio.AudioPlayer;
import playlist.PlaylistManager;
import utils.ConsoleStyle;

/**
 * Rewinds the currently playing song by a set number of seconds.
 */
public class SeekBackward implements Command {

    private final PlaylistManager playlistManager;
    private final AudioPlayer audioPlayer;
    private final long secondsToSeek;

    public SeekBackward(PlaylistManager playlistManager, AudioPlayer audioPlayer, long secondsToSeek) {
        this.playlistManager = playlistManager;
        this.audioPlayer = audioPlayer;
        this.secondsToSeek = secondsToSeek;
    }

    /**
     * Executes the seek backward command.
     *
     * @return message indicating the result of the backward seek.
     */
    public String execute() {
        if (!playlistManager.hasCurrentPlaylist()) {
            return ConsoleStyle.color("❌ No playlist selected." , ConsoleStyle.RED);
        }

        return audioPlayer.seekBackward(secondsToSeek);
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
