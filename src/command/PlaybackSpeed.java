package command;

import audio.AudioPlayer;
import playlist.PlaylistManager;

/**
 * Sets the playback speed for the current audio.
 */
public class PlaybackSpeed implements Command {

    private final PlaylistManager playlistManager;
    private final AudioPlayer audioPlayer;
    private final double speed;

    public PlaybackSpeed(PlaylistManager playlistManager, AudioPlayer audioPlayer, double speed) {
        this.playlistManager = playlistManager;
        this.audioPlayer = audioPlayer;
        this.speed = speed;
    }

    /**
     * Executes the command to set playback speed.
     *
     * @return a status message indicating success or failure
     */
    @Override
    public String execute() {
        if (!playlistManager.hasCurrentPlaylist()) {
            return "❌ No playlist selected.";
        }

        return audioPlayer.setPlaybackSpeed(speed);
    }


}
