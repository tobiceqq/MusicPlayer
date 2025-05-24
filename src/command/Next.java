package command;

import audio.AudioPlayer;
import model.PlayMode;
import model.Song;
import playlist.PlaylistManager;

import java.util.List;
import java.util.Random;

/**
 * Skips to the next song in the current playlist based on the current play mode.
 */
public class Next implements Command {

    private final PlaylistManager playlistManager;
    private final AudioPlayer audioPlayer;
    private final Random random;

    public Next(PlaylistManager playlistManager, AudioPlayer audioPlayer) {
        this.playlistManager = playlistManager;
        this.audioPlayer = audioPlayer;
        this.random = new Random();
    }

    @Override
    public String execute() {
        if (!playlistManager.hasCurrentPlaylist()) {
            return "❌ No playlist selected.";
        }

        List<Song> songs = playlistManager.getCurrentPlaylist().getSongs();
        if (songs.isEmpty()) {
            return "\uD83D\uDCED Playlist is empty";
        }

        int currentIndex = playlistManager.getCurrentPlaylist().getCurrentSongIndex();
        PlayMode mode = playlistManager.getCurrentPlaylist().getPlayMode();
        int nextIndex = currentIndex;

        switch (mode) {
            case NORMAL:
                nextIndex++;
                if (nextIndex >= songs.size()) {
                    return "\uD83D\uDEAB End of playlist.";
                }
                break;

            case LOOP_ONE:
                // Keep the same index
                break;

            case LOOP_ALL:
                nextIndex = (currentIndex + 1) % songs.size();
                break;

            case SHUFFLE:
                nextIndex = random.nextInt(songs.size());
                break;

            case FAVORITES:
                nextIndex = findNextFavorite(songs, currentIndex);
                if (nextIndex == -1) {
                    return "\uD83D\uDEAB No more favorite songs.";
                }
                break;
        }

        playlistManager.getCurrentPlaylist().setCurrentSongIndex(nextIndex);
        Song songToPlay = songs.get(nextIndex);
        songToPlay.incrementPlayCount();

        return "▶\uFE0F Now playing: " + songToPlay;
    }

    /**
     * Finds the next favorite song in the list, starting after given index.
     *
     * @param songs the list of songs
     * @param currentIndex the current song index
     * @return index of the next favorite song, or -1 if not found
     */
    private int findNextFavorite(List<Song> songs, int currentIndex) {
        for (int i = currentIndex + 1; i < songs.size(); i++) {
            if (songs.get(i).isFavorite()) {
                return i;
            }
        }
        return -1;
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
