package tests;

import audio.AudioPlayer;
import model.PlayMode;
import model.Playlist;
import org.junit.Before;
import org.junit.Test;
import playlist.PlaylistManager;
import model.Song;

import static org.junit.jupiter.api.Assertions.*;
public class AudioPlayerTest {

    private AudioPlayer audioPlayer;
    private PlaylistManager playlistManager;
    private Playlist playlist;
    private Song song1;
    private Song song2;

    @Before
    public void setUp() {
        playlistManager = new PlaylistManager();
        audioPlayer = new AudioPlayer(playlistManager);

        playlist = new Playlist("LYFESTYLE", "Yeat");
        song1 = new Song("FOREVER AGAIN" , "Yeat", 200, "src/songs/FOREVER AGAIN.wav");
        song2 = new Song("STFU" , "Yeat", 104, "src/songs/STFU.wav");

        playlist.addSong(song1);
        playlist.addSong(song2);
    }

    @Test
    public void testStopWhenNothingIsPlaying() {
        String result = audioPlayer.stop();
        assertTrue(result.contains("Nothing was playing"));
    }

    @Test
    public void testPauseWhenNothingIsPlaying() {
        String result = audioPlayer.pause();
        assertTrue(result.contains("Nothing is playing"));
    }

    @Test
    public void testShufflePlaylist() {
        String result = audioPlayer.shuffle(playlist);
        assertNotNull(result);
        assertTrue(result.contains("\uD83D\uDD00 Playlist has been shuffled."));

        assertEquals(PlayMode.SHUFFLE, playlist.getPlayMode());
        assertEquals(0, playlist.getCurrentSongIndex());
    }

    @Test
    public void testShuffleEmptyPlaylist() {
        Playlist empty = new Playlist("none", "none");
        String result = audioPlayer.shuffle(empty);
        assertTrue(result.contains("Cannot shuffle an empty playlist"));
    }



    }
