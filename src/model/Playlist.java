package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a playlist containing songs.
 */
public class Playlist {
    private String name;
    private List<Song> songs;

    public Playlist(String name) {
        this.name = name;
        this.songs = new ArrayList<>();
    }

    /**
     * Adds a song to the playlist.
     *
     * @param song the song to add
     */
    public void addSong(Song song) {
        songs.add(song);
    }

    /**
     * Removes a song from the playlist.
     *
     * @param song the song to remove
     * @return true if the song was removed, false otherwise
     */
    public boolean removeSong(Song song) {
        return songs.remove(song);
    }

    /**
     * Returns all songs in the playlist.
     *
     * @return the list of songs.
     */
    public List<Song> getSongs() {
        return new ArrayList<>(songs);
    }

    /**
     * Finds a song by title.
     *
     * @param title the title to search for
     * @return the found song, or null if not found
     */
    public Song findSongByTitle(String title) {
        for (Song song : songs) {
            if (song.getTitle().equalsIgnoreCase(title)) {
                return song;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        if (songs.isEmpty()) {
            return "Playlist '" + name + "' is empty.";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Playlist: ").append(name).append("\n");
        for (int i = 0; i < songs.size(); i++) {
            sb.append((i + 1)).append(". ").append(songs.get(i).toString()).append("\n");
        }
        return sb.toString();
    }
}
