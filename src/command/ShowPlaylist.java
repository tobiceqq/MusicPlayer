package command;

import model.Song;
import playlist.PlaylistManager;
import utils.ConsoleStyle;

import java.util.List;

/**
 * Displays tje contents of the current playlist.
 */
public class ShowPlaylist implements Command {

    private final PlaylistManager playlistManager;

    public ShowPlaylist(PlaylistManager playlistManager) {
        this.playlistManager = playlistManager;
    }

    /**
     * Shows a numbered list of all songs in the selected playlist.
     *
     * @return formatted playlist contents or error message
     */
    public String execute() {
        if (!playlistManager.hasCurrentPlaylist()) {
            return ConsoleStyle.color("❌ No playlist selected." , ConsoleStyle.RED);
        }

        List<Song> songs = playlistManager.getCurrentPlaylist().getSongs();
        if (songs.isEmpty()) {
            return ConsoleStyle.color("\uD83D\uDCED Playlist is empty.", ConsoleStyle.BLUE);
        }

        StringBuilder sb = new StringBuilder("\uD83D\uDCC3 Songs in playlist:\n");

        for (int i = 0; i < songs.size(); i++) {
            Song s = songs.get(i);
            sb.append(String.format("%2d. %s – %s (%d:%02d)%s\n" , i + 1 , s.getArtist() , s.getTitle() ,
                    s.getDurationInSeconds() / 60, s.getDurationInSeconds() % 60 , s.isFavorite() ? " ❤\uFE0F" : ""));
        }
        return sb.toString().strip();
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
