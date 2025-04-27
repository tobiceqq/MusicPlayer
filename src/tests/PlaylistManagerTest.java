package tests;

import org.junit.jupiter.api.Test;
import playlist.PlaylistManager;
import model.Playlist;

import static org.junit.jupiter.api.Assertions.*;
class PlaylistManagerTest {

    @Test
    void testCreateAndGetPlaylist() {
        PlaylistManager manager = new PlaylistManager();
        manager.createPlaylist("UTOPIA");

        Playlist playlist = manager.getPlaylist("UTOPIA");
        assertNotNull(playlist, "Playlist 'UTOPIA' should exist after being created");
        assertEquals("UTOPIA", playlist.getName(), "Playlist name should be 'UTOPIA'");
    }

    @Test
    void testRemovePlaylist() {
        PlaylistManager manager = new PlaylistManager();
        manager.createPlaylist("MUSIC");
        manager.removePlaylist("MUSIC");

        Playlist playlist = manager.getPlaylist("MUSIC");
        assertNull(playlist, "Playlist 'MUSIC' should not exist after being removed");
    }

    @Test
    void testSelectPlaylist() {
        PlaylistManager manager = new PlaylistManager();
        manager.createPlaylist("More Chaos");

        boolean selected = manager.selectPlaylist("More Chaos");
        assertTrue(selected, "Should successfully select existing playlist");

        Playlist current = manager.getCurrentPlaylist();
        assertNotNull(current, "Current playlist should not be null after being selected");
        assertEquals("More Chaos", current.getName(), "Selected playlist should be 'More Chaos'");
    }

    @Test
    void testSelectNonExistentPlaylist() {
        PlaylistManager manager = new PlaylistManager();

        boolean selected = manager.selectPlaylist("NonExistent");
        assertFalse(selected, "Should not select a non-existing playlist");
        assertNull(manager.getCurrentPlaylist(), "Should have no current playlist initially");
    }

    @Test
    void testHasCurrentPlaylist() {
        PlaylistManager manager = new PlaylistManager();
        assertFalse(manager.hasCurrentPlaylist(), "Should have no current playlist initially");

        manager.createPlaylist("2093");
        manager.selectPlaylist("2093");
        assertTrue(manager.hasCurrentPlaylist(), "Should have current playlist after selection");
    }
  
}