package app;

import player.MusicPlayer;

/**
 * Entry point of the KYTE Music Player app.
 */
public class Main {
    public static void main(String[] args) {
        MusicPlayer musicPlayer = new MusicPlayer();
        musicPlayer.run();
    }
}