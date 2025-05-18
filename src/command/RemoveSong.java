package command;

import audio.AudioPlayer;
import model.Song;
import playlist.PlaylistManager;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Removes a song from the current playlist by title.
 */
public class RemoveSong implements Command{

    private final PlaylistManager playlistManager;
    private final Scanner scanner;

    public RemoveSong(PlaylistManager playlistManager, Scanner scanner) {
        this.playlistManager = playlistManager;
        this.scanner = scanner;
    }

    /**
     * Executes the remove song command.
     *
     * @return result message
     */
    public String execute() {
        if (!playlistManager.hasCurrentPlaylist()) {
            return "❌ No playlist selected.";
        }

        List<Song> songs = playlistManager.getCurrentPlaylist().getSongs();
        if (songs.isEmpty()) {
            return "\uD83D\uDCED Playlist is empty.";
        }

        System.out.println("\uD83D\uDD0D Enter the title of the song to remove: ");
        String title = scanner.nextLine().trim();

        Iterator<Song> iterator = songs.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            Song song = iterator.next();
            if (song.getTitle().equalsIgnoreCase(title)) {
                iterator.remove();

                int currentIndex = playlistManager.getCurrentPlaylist().getCurrentSongIndex();
                if (index < currentIndex) {
                    playlistManager.getCurrentPlaylist().setCurrentSongIndex(currentIndex - 1);
                } else if (index == currentIndex && currentIndex >= songs.size()) {
                    playlistManager.getCurrentPlaylist().setCurrentSongIndex(Math.max(0, songs.size() -1));
                }

                return "\uD83D\uDDD1\uFE0F Song" + song.getTitle() + " has been removed from the playlist.";
            }
            index++;
        }
        return "⚠\uFE0F Song not found in the playlist.";
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



