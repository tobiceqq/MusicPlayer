package command;

import audio.AudioPlayer;
import playlist.PlaylistManager;

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
            return "❌ No playlist selected.";
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
