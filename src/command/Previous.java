package command;

import audio.AudioPlayer;
import model.PlayMode;
import model.Song;
import playlist.PlaylistManager;
import utils.ConsoleStyle;

import java.util.List;

/**
 * Plays the previous song in the current playlist.
 */
public class Previous implements Command {

    private final PlaylistManager playlistManager;
    private final AudioPlayer audioPlayer;

    public Previous(PlaylistManager playlistManager, AudioPlayer audioPlayer) {
        this.playlistManager = playlistManager;
        this.audioPlayer = audioPlayer;
    }

    /**
     * Executes the previous command.
     *
     * @return message indicating the result of playback
     */
    public String execute() {
        if (!playlistManager.hasCurrentPlaylist()) {
            return ConsoleStyle.color("❌ No playlist selected." , ConsoleStyle.RED);
        }

        List<Song> songs = playlistManager.getCurrentPlaylist().getSongs();
        if (songs.isEmpty()) {
            return ConsoleStyle.color("\uD83D\uDCED Playlist is empty.", ConsoleStyle.BLUE);
        }

        int currentIndex = playlistManager.getCurrentPlaylist().getCurrentSongIndex();
        PlayMode playMode = playlistManager.getCurrentPlaylist().getPlayMode();

        int previousIndex = currentIndex;

        switch (playMode) {
            case NORMAL:
            case LOOP_ONE:
            case LOOP_ALL:
                previousIndex = currentIndex - 1;
                if (previousIndex < 0) {
                    return "\uD83D\uDEAB This is the first song.";
                }
                break;
            case SHUFFLE:
                previousIndex = (int) (Math.random() * songs.size());
                break;
            case FAVORITES:
                previousIndex = findPreviousFavorite(songs, currentIndex);
                if (previousIndex == -1) {
                    return "\uD83D\uDEAB No previous favorite songs.";
                }
                break;
        }

        playlistManager.getCurrentPlaylist().setCurrentSongIndex(previousIndex);
        Song songToPlay = songs.get(previousIndex);
        songToPlay.incrementPlayCount();

        return audioPlayer.play(songToPlay);
    }

    private int findPreviousFavorite(List<Song> songs , int fromIndex) {
        for (int i = fromIndex - 1; i >= 0 ; i--) {
            if (songs.get(i).isFavorite()) {
                return i;
            }
        }
        return -1;
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