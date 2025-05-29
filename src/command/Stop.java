package command;

import audio.AudioPlayer;
import playlist.PlaylistManager;
import utils.ConsoleStyle;

/**
 * Completely stops the current playback.
 */
public class Stop implements Command{

    private final PlaylistManager playlistManager;
    private final AudioPlayer audioPlayer;

    public Stop(PlaylistManager playlistManager, AudioPlayer audioPlayer) {
        this.playlistManager = playlistManager;
        this.audioPlayer = audioPlayer;
    }

    /**
     * Stops the playback and resets the song position.
     *
     * @return Message confirming the playback has been stopped.
     */
    @Override
    public String execute() {
        if (!playlistManager.hasCurrentPlaylist()) {
            return ConsoleStyle.color("❌ No playlist selected." , ConsoleStyle.RED);
        }

        if (!audioPlayer.isPlaying()) {
            return ConsoleStyle.color("⏹\uFE0F Nothing is currently playing." , ConsoleStyle.CYAN);
        }

        audioPlayer.stop();

        return ConsoleStyle.color("⏹\uFE0F Playback stopped completely." , ConsoleStyle.GREEN);
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
