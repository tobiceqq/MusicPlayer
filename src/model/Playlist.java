package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a playlist containing songs.
 */
public class Playlist {

    private String name;
    private String artist;
    private List<Song> songs;
    private PlayMode playMode = PlayMode.NORMAL;
    private int currentSongIndex = 0;

    public Playlist(String name, String artist) {
        this.name = name;
        this.artist = artist;
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

    public void removeSong(Song song) {
        songs.remove(song);
    }

    /**
     * Returns all songs in the playlist.
     *
     * @return the list of songs.
     */
    public List<Song> getSongs() {
        return songs;
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

    public PlayMode getPlayMode() {
        return playMode;
    }

    public void setPlayMode(PlayMode playMode) {
        this.playMode = playMode;
    }

    public int getCurrentSongIndex() {
        return currentSongIndex;
    }

    public void setCurrentSongIndex(int index) {
        if (index >= 0 && index < songs.size()) {
            this.currentSongIndex = index;
        }
    }

    public boolean isEmpty() {
        return songs == null || songs.isEmpty();
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
