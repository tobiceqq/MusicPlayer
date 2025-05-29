package command;

import audio.AudioPlayer;
import model.PlayMode;
import model.Playlist;
import model.Song;
import playlist.PlaylistManager;
import utils.ConsoleStyle;

import java.util.List;

/**
 * Shuffles songs in the current playlist.
 */
public class Shuffle implements Command {

    private final PlaylistManager playlistManager;
    private final AudioPlayer audioPlayer;

    public Shuffle(PlaylistManager playlistManager, AudioPlayer audioPlayer) {
        this.playlistManager = playlistManager;
        this.audioPlayer = audioPlayer;
    }

    /**
     * Sets play mode to shuffle for the selected playlist.
     *
     * @return result message or error
     */
    public String execute() {
        if (!playlistManager.hasCurrentPlaylist()) {
            return ConsoleStyle.color("❌ No playlist selected." , ConsoleStyle.RED);
        }

        List<Song> songs = playlistManager.getCurrentPlaylist().getSongs();
        if (songs.isEmpty()) {
            return ConsoleStyle.color("\uD83D\uDCED Playlist is empty.", ConsoleStyle.BLUE);
        }

        return audioPlayer.shuffle(playlistManager.getCurrentPlaylist());
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
