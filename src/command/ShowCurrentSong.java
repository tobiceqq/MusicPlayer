package command;

import model.Song;
import playlist.PlaylistManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
     * Loads the song_info.txt template and fills it with the current song's data.
     *
     * @return formatted song info or an error message
     */
    public String execute() {
        if (!playlistManager.hasCurrentPlaylist()) {
            return "❌ No playlist selected.";
        }

        List<Song> songs = playlistManager.getCurrentPlaylist().getSongs();
        if (songs.isEmpty()) {
            return "\uD83D\uDCED Playlist is empty.";
        }

        int index = playlistManager.getCurrentPlaylist().getCurrentSongIndex();
        if (index < 0 || index >= songs.size()) {
            return "⚠\uFE0F Invalid song index.";
        }

        Song song = songs.get(index);

        try {
            String template = Files.readString(Paths.get("song_info.txt"));
            String result = template
                    .replace("${title}", song.getTitle())
                    .replace("${artist}", song.getArtist())
                    .replace("${length}", String.format("%d:%02d", song.getDurationInSeconds() / 60, song.getDurationInSeconds() % 60))
                    .replace("${favorite}", song.isFavorite() ? "Yes" : "No")
                    .replace("${playCount}", String.valueOf(song.getPlayCount()));

            return result;
        } catch (IOException e) {
            return "❌ Could not read song_info.txt: " + e.getMessage();
        }
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
