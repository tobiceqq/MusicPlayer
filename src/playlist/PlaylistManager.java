package playlist;

import model.Playlist;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages multiple playlists in the music player.
 */
public class PlaylistManager {

    private Map<String, Playlist> playlists;
    private List<Playlist> playlists2;
    private Playlist currentPlaylist;

    public PlaylistManager() {
        this.playlists = new HashMap<>();
    }

    /**
     * Creates a new playlist with the given name.
     *
     * @param name the name of the new playlist
     */
    public void createPlaylist(String name, String artist) {
        playlists.put(name.toLowerCase(), new Playlist(name, artist));
    }


    /**
     * Removes a playlist with the given name.
     *
     * @param name the name of the playlist to remove
     */
    public void removePlaylist(String name) {
        playlists.remove(name.toLowerCase());
    }

    /**
     * Sets the current playlist to work with.
     *
     * @param name the name of the playlist
     * @return true if the playlist was found and set, false otherwise
     */
    public boolean selectPlaylist(String name) {
        Playlist playlist = playlists.get(name.toLowerCase());
        if (playlist != null) {
            currentPlaylist = playlist;
            return true;
        }
        return false;
    }

    /**
     * Shows the playlist based on its name.
     *
     * @param name Name of the playlist.
     * @return the playlist
     */
    public Playlist getPlaylistByName(String name) {
       return playlists.get(name.toLowerCase());
            }


    /**
     * Returns the currently selected playlist.
     *
     * @return the current playlist
     */
    public Playlist getCurrentPlaylist() {
        return currentPlaylist;
    }

    /**
     * Gets a playlist by its name.
     *
     * @param name the name of the playlist
     * @return the found playlist, or null if not found
     */
    public Playlist getPlaylist(String name) {
        return playlists.get(name.toLowerCase());
    }

    public boolean hasCurrentPlaylist() {
        return currentPlaylist != null;
    }

}
