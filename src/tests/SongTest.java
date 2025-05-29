package tests;

import model.Song;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;
public class SongTest {

    private Song song;

    @Before
    public void setUp() {
        song = new Song("LVL", "A$AP Rocky", 220, "src/songs/LVL.wav");
    }

    @Test
    public void testGetters() {
        assertEquals("LVL", song.getTitle());
        assertEquals("A$AP Rocky", song.getArtist());
        assertEquals(220, song.getDurationInSeconds());
        assertEquals("src/songs/LVL.wav", song.getFilePath());
        assertEquals(0, song.getPlayCount());
        assertFalse(song.isFavorite());
    }

    @Test
    public void testIncrementPlaycount() {
        assertEquals(0, song.getPlayCount());
        song.incrementPlayCount();
        assertEquals(1, song.getPlayCount());
        song.incrementPlayCount();
        assertEquals(2, song.getPlayCount());
    }

    @Test
    public void testSetFavorite() {
        assertFalse(song.isFavorite());
        song.setFavorite(true);
        assertTrue(song.isFavorite());
        song.setFavorite(false);
        assertFalse(song.isFavorite());
    }

    @Test
    public void testToStringWithoutFavorite() {
        String expected = "A$AP Rocky – LVL [3:40]";
        assertEquals(expected, song.toString());
    }

    @Test
    public void testToStringWithFavorite() {
        String expected = "A$AP Rocky – LVL [3:40]";
        assertEquals(expected, song.toString());
    }
  
}