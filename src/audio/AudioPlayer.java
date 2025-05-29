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
import utils.ConsoleStyle;

public class AudioPlayer {

    private Clip clip;
    private Long pausedPosition = 0L;
    private Song currentlyPlayingSong;
    private final PlaylistManager playlistManager;

    public AudioPlayer(PlaylistManager playlistManager) {
        this.playlistManager = playlistManager;
    }

    /**
     * Plays the audio file from the beginning.
     *
     * @param song the song to be played
     * @return a message indicating success or error
     */
    public String play(Song song) {

        if (clip != null && currentlyPlayingSong == song && pausedPosition > 0) {
            clip.setMicrosecondPosition(pausedPosition);
            clip.start();
            pausedPosition = 0L;
            return "▶\uFE0F Resuming: " + ConsoleStyle.bold(song.getTitle());
        }

        stop();

        try {
            File audioFile = new File(song.getFilePath());
            if (!audioFile.exists()) {
                return "❌ File not found: " + ConsoleStyle.bold(song.getFilePath());
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();

            currentlyPlayingSong = song;
            pausedPosition = 0L;

            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP && clip.getMicrosecondPosition() >= clip.getMicrosecondLength()) {
                    clip.close();

                    Playlist currentPlaylist = playlistManager.getCurrentPlaylist();
                    int currentIndex = currentPlaylist.getCurrentSongIndex();

                    if (currentIndex + 1 < currentPlaylist.getSongs().size()) {
                        currentPlaylist.setCurrentSongIndex(currentIndex + 1);

                        Song nextSong = currentPlaylist.getSongs().get(currentIndex + 1);

                        System.out.println(play(nextSong));
                    }
                }
            });

            return "▶\uFE0F Now playing: " + ConsoleStyle.bold(song.getTitle());

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            return "❌ Error playing file: " + ConsoleStyle.bold(e.getMessage());
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
            return ConsoleStyle.color("⏸\uFE0F Playback paused." , ConsoleStyle.CYAN);
        }
        return ConsoleStyle.color("⚠\uFE0F Nothing is playing." , ConsoleStyle.YELLOW);
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
            return ConsoleStyle.color("⏸\uFE0F Playback paused." , ConsoleStyle.CYAN);
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


    private boolean isPlaying;

    /**
     * Shuffles the currently using playlist.
     * @param playlist the playlist to shuffle
     * @return message about it being shuffled or error
     */
    public String shuffle(Playlist playlist) {
        if (playlist.getSongs().isEmpty()) {
            return "\uD83D\uDCED Cannot shuffle an empty playlist.";
        }

        Collections.shuffle(playlist.getSongs());
        playlist.setCurrentSongIndex(0);
        playlist.setPlayMode(PlayMode.SHUFFLE);

        return "\uD83D\uDD00 Playlist has been shuffled.";
    }

    public boolean isPlaying() {
        return clip != null && clip.isRunning();
    }
}
