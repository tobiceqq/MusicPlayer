package command;

import playlist.PlaylistManager;
import utils.InputValidator;
import model.Song;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Adds a new song to the currently selected playlist.
 *
 * Requires the user to input the title, artist and duration.
 * Validates the input and updates the playlist accordingly.
 */
public class AddSong implements Command {

    private final PlaylistManager playlistManager;
    private final Scanner scanner;

    public AddSong(PlaylistManager playlistManager, Scanner scanner) {
        this.playlistManager = playlistManager;
        this.scanner = scanner;
    }

    /**
     * Executes the command to add a song to the current playlist.
     *
     * @return result message indicating success or failure
     */
    @Override
    public String execute() {
        if (!playlistManager.hasCurrentPlaylist()) {
            return "❌ No playlist selected. Use 'select' or 'create' to choose a playlist first.";
        }

        // 1. soubor
        System.out.println("\uD83D\uDCBE Enter full path to .wav file: ");
        String filePath = scanner.nextLine().trim();

        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            return "❌ File not found: " + filePath;
        }

        // 2. nazev
        System.out.println("\uD83C\uDFB5 Enter song title: ");
        String title = scanner.nextLine().trim();
        if (title.isEmpty()) {
            return "⚠\uFE0F Title cannot be empty.";
        }

        // 3. interpret
        System.out.println("\uD83D\uDC64 Enter artist name: ");
        String artist = scanner.nextLine().trim();
        if (artist.isEmpty()) {
            return "⚠\uFE0F Artist name cannot be empty.";
        }

        int durationInSeconds;
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            AudioFormat format = audioInputStream.getFormat();
            long frames = audioInputStream.getFrameLength();
            durationInSeconds = (int) (frames / format.getFrameRate());
        } catch (UnsupportedAudioFileException | IOException e) {
            return "❌ Unable to read audio file: " + e.getMessage();
        }

        Song newSong = new Song(title, artist, durationInSeconds, filePath);

        playlistManager.getCurrentPlaylist().addSong(newSong);
        return "✅ Song added: " + newSong;
    }

    /**
     * Indicates whether this command should cause the program to exit.
     *
     * @return false, as this commands does not terminate the program
     */
    @Override
    public boolean exit() {
        return false;
    }
}
