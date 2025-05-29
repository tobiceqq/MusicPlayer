package command;

import model.Song;
import playlist.PlaylistManager;
import utils.ConsoleStyle;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Displays the currently playing song in the active playlist
 */
public class ShowCurrentSong implements Command {

    private final PlaylistManager playlistManager;

    public ShowCurrentSong(PlaylistManager playlistManager) {
        this.playlistManager = playlistManager;
    }

    /**
     * Displays the current song's details formatted directly in code.
     *
     * @return formatted song info or an error message
     */
    public String execute() {
        if (!playlistManager.hasCurrentPlaylist()) {
            return ConsoleStyle.color("❌ No playlist selected." , ConsoleStyle.RED);
        }

        List<Song> songs = playlistManager.getCurrentPlaylist().getSongs();
        if (songs.isEmpty()) {
            return ConsoleStyle.color("\uD83D\uDCED Playlist is empty.", ConsoleStyle.BLUE);
        }

        int index = playlistManager.getCurrentPlaylist().getCurrentSongIndex();
        if (index < 0 || index >= songs.size()) {
            return "⚠\uFE0F Invalid song index.";
        }

        Song song = songs.get(index);

        String output = "\n\uD83C\uDFA7 Now playing: " + ConsoleStyle.bold(song.getTitle())
                + "\n\uD83C\uDFA4 Artist: " + ConsoleStyle.bold(song.getArtist())
                + "\n⏱\uFE0F Length: " + ConsoleStyle.bold(String.format("%d:%02d" , song.getDurationInSeconds() / 60, song.getDurationInSeconds() & 60))
                + "\n\uD83D\uDCAB Played: " + ConsoleStyle.bold(String.valueOf(song.getPlayCount()))
                + "\n❤\uFE0F Favorite: " + (song.isFavorite() ? "✅" : "❌");

        return output;
    }

    /**l
     * Indicates whether this command should cause the program to exit.
     *
     * @return false, as this commands does not terminate the program
     */
    @Override
    public boolean exit() {
        return false;
    }
}
