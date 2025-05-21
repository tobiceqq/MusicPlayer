package audio;

import javax.sound.sampled.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class AudioPlayer {

    private Clip clip;
    private Long pausedPosition = 0L;

    /**
     * Plays the audio file from the beginning.
     *
     * @param filePath the path to the .wav audio file
     * @return a message indicating success or error
     */
    public String play(String filePath) {
        stop();

        try {
            File audioFile = new File(filePath);
            if (!audioFile.exists()) {
                return "❌ File not found: " + filePath;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            return "▶\uFE0F Now playing: " + audioFile.getName();

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

    public double getPlaybackSpeed() {
        return playbackSpeed;
    }

    public boolean isPlaying() {
        return clip != null && clip.isRunning();
    }
}
