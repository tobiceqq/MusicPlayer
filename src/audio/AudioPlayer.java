package audio;

import model.PlayMode;
import playlist.PlaylistManager;

import javax.sound.sampled.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import model.Song;

import model.Playlist;

public class AudioPlayer {

    private Clip clip;
    private Long pausedPosition = 0L;

    /**
     * Plays the audio file from the beginning.
     *
     * @param song the song to be played
     * @return a message indicating success or error
     */
    public String play(Song song) {
        stop();

        try {
            File audioFile = new File(song.getFilePath());
            if (!audioFile.exists()) {
                return "❌ File not found: " + song.getFilePath();
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            return "▶\uFE0F Now playing: " + song.getTitle();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            return "❌ Error playing file: " + e.getMessage();
        }
    }

    /**
     * Resumes the playback from the paused position.
     *
     * @return resume status message
     */
    public String pause() {
        if (clip != null && clip.isRunning()) {
            pausedPosition = clip.getMicrosecondPosition();
            clip.stop();
            return "⏸\uFE0F Playback paused.";
        }
        return "⚠\uFE0F Nothing is playing.";
    }

    /**
     * Stops playback and releases resources.
     *
     * @return stop status message
     */
    public String stop() {
        if (clip != null) {
            clip.stop();
            clip.close();
            clip = null;
            pausedPosition = 0L;
            return "⏹\uFE0F Playback stopped.";
        }
        return "⚠\uFE0F Nothing was playing.";
    }

    /**
     * Rewinds the current audio by a given number of seconds.
     *
     * @param seconds how many seconds to rewind
     * @return status message
     */
    public String seekBackward(long seconds) {
        if (clip == null || !clip.isOpen()) {
            return "❌ No audio loaded.";
        }

        long currentPosition = clip.getMicrosecondPosition();
        long newPosition = Math.max(0 , currentPosition - seconds * 1_000_000);
        clip.setMicrosecondPosition(newPosition);

        return "⏪ Rewound " + seconds + " seconds.";
    }

    /**
     * Fast-forwards the current audio by a given number of seconds.
     *
     * @param seconds how many seconds to forward
     * @return status message
     */
    public String seekForward(long seconds) {
        if (clip == null || !clip.isOpen()) {
            return "❌ No audio loaded.";
        }

        long currentPosition = clip.getMicrosecondPosition();
        long clipLength = clip.getMicrosecondLength();
        long newPosition = Math.min(clipLength , currentPosition + seconds * 1_000_000);
        clip.setMicrosecondPosition(newPosition);

        return "⏩ Forwarded " + seconds + " seconds.";
    }

    private double playbackSpeed = 1.0;
    private boolean isPlaying;
    private Thread currentPlaybackThread;

    public String setPlaybackSpeed(double speed) {
        if (speed <= 0)
            return "❌ Invalid speed.";
        this.playbackSpeed = speed;
        return "⏩ Playback speed set to " + speed + "x.";
    }

    public String shuffle(Playlist playlist) {
        if (playlist.getSongs().isEmpty()) {
            return "\uD83D\uDCED Cannot shuffle an empty playlist.";
        }

        Collections.shuffle(playlist.getSongs());
        playlist.setCurrentSongIndex(0);
        playlist.setPlayMode(PlayMode.SHUFFLE);

        return "\uD83D\uDD00 Playlist has been shuffled.";
    }

    public double getPlaybackSpeed() {
        return playbackSpeed;
    }

    public boolean isPlaying() {
        return clip != null && clip.isRunning();
    }
}
