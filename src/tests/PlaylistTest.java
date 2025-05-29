package tests;

import model.PlayMode;
import model.Playlist;
import model.Song;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlaylistTest {

    private Playlist playlist;
    private Song song1;
    private Song song2;

    @BeforeEach
    public void setUp() {
        playlist = new Playlist("LYFESTYLE", "Yeat");
        song1 = new Song("FOREVER AGAIN" , "Yeat", 200, "src/songs/FOREVER AGAIN.wav");
        song2 = new Song("STFU" , "Yeat", 104, "src/songs/STFU.wav");
    }

    @Test
    public void testAddSong() {
        playlist.addSong(song1);
        assertEquals(1, playlist.getSongs().size());
        assertEquals(song1, playlist.getSongs().get(0));
    }

    @Test
    public void testRemoveSong() {
        playlist.addSong(song1);
        playlist.addSong(song2);

        assertEquals(2, playlist.getSongs().size());
        playlist.removeSong(song1);
        assertEquals(1, playlist.getSongs().size());
        assertEquals(song2, playlist.getSongs().get(0));
    }

    @Test
    public void testSetPlayMode() {
        playlist.setPlayMode(PlayMode.SHUFFLE);
        assertEquals(PlayMode.SHUFFLE, playlist.getPlayMode());
    }

    @Test
    public void testFindSongByTitle() {
        playlist.addSong(song1);
        playlist.addSong(song2);
        Song found = playlist.findSongByTitle("STFU");
        assertNotNull(found);
        assertEquals(song2, found);
    }
}
