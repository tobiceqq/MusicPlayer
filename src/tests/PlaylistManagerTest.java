package tests;

import org.junit.jupiter.api.Test;
import playlist.PlaylistManager;
import model.Playlist;

import static org.junit.jupiter.api.Assertions.*;
public class PlaylistManagerTest {

    @Test
    public void testCreateAndGetPlaylist() {
        PlaylistManager manager = new PlaylistManager();
        manager.createPlaylist("UTOPIA" , "Travis Scott");

        Playlist playlist = manager.getPlaylist("UTOPIA");
        assertNotNull(playlist, "Playlist 'UTOPIA' should exist after being created");
        assertEquals("UTOPIA", playlist.getName(), "Playlist name should be 'UTOPIA'");
    }

    @Test
    public void testRemovePlaylist() {
        PlaylistManager manager = new PlaylistManager();
        manager.createPlaylist("MUSIC" , "Playboi Carti");
        manager.removePlaylist("MUSIC");

        Playlist playlist = manager.getPlaylist("MUSIC");
        assertNull(playlist, "Playlist 'MUSIC' should not exist after being removed");
    }

    @Test
    public void testSelectPlaylist() {
        PlaylistManager manager = new PlaylistManager();
        manager.createPlaylist("More Chaos" , "Ken Carson");

        boolean selected = manager.selectPlaylist("More Chaos");
        assertTrue(selected, "Should successfully select existing playlist");

        Playlist current = manager.getCurrentPlaylist();
        assertNotNull(current, "Current playlist should not be null after being selected");
        assertEquals("More Chaos", current.getName(), "Selected playlist should be 'More Chaos'");
    }

    @Test
    public void testSelectNonExistentPlaylist() {
        PlaylistManager manager = new PlaylistManager();

        boolean selected = manager.selectPlaylist("NonExistent");
        assertFalse(selected, "Should not select a non-existing playlist");
        assertNull(manager.getCurrentPlaylist(), "Should have no current playlist initially");
    }

    @Test
    public void testHasCurrentPlaylist() {
        PlaylistManager manager = new PlaylistManager();
        assertFalse(manager.hasCurrentPlaylist(), "Should have no current playlist initially");

        manager.createPlaylist("2093" , "Yeat");
        manager.selectPlaylist("2093");
        assertTrue(manager.hasCurrentPlaylist(), "Should have current playlist after selection");
    }
  
}