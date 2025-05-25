package player;

import audio.AudioPlayer;
import command.CommandExecutor;
import exceptions.InvalidCommandException;
import playlist.PlaylistManager;

import java.util.Scanner;

/**
 * Main logic handler for the music player app.
 */
public class MusicPlayer {

    private final PlaylistManager playlistManager;
    private final AudioPlayer audioPlayer;
    private final CommandExecutor commandExecutor;

    public MusicPlayer() {
        this.playlistManager = new PlaylistManager();
        this.audioPlayer = new AudioPlayer();
        this.commandExecutor = new CommandExecutor(playlistManager, audioPlayer);
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\uD83C\uDFB5 Welcome to KYTE Music Player!");
        System.out.println("Type 'help' to see available commands.\n");

        while (true) {
            System.out.println("\uD83C\uDFA7 > ");
            String input = scanner.nextLine().trim();

            try {
                String output = commandExecutor.executeCommand(input);
                System.out.println(output);
                if (commandExecutor.shouldExit(input)) break;

            } catch (InvalidCommandException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("\uD83D\uDC4B Goodbye!");
    }
}
