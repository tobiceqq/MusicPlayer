package command;

import audio.AudioPlayer;
import model.Song;
import playlist.PlaylistManager;
import utils.ConsoleStyle;

/**
 * Plays the currently selected song in the active playlist.
 */
public class Play implements Command {

    private final PlaylistManager playlistManager;
    private final AudioPlayer audioPlayer;

    public Play(PlaylistManager playlistManager, AudioPlayer audioPlayer) {
        this.playlistManager = playlistManager;
        this.audioPlayer = audioPlayer;
    }

    /**
     * Executes the play command.
     *
     * @return a status message after attempting playback
     */
    @Override
    public String execute() {
        if (!playlistManager.hasCurrentPlaylist()) {
            return ConsoleStyle.color("❌ No playlist selected." , ConsoleStyle.RED);
        }

        if (playlistManager.getCurrentPlaylist().isEmpty()) {
            return ConsoleStyle.color("\uD83D\uDCED Playlist is empty.", ConsoleStyle.BLUE);
        }

        int index = playlistManager.getCurrentPlaylist().getCurrentSongIndex();
        Song currentSong = playlistManager.getCurrentPlaylist().getSongs().get(index);

        return audioPlayer.play(currentSong);
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
