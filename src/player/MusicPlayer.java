package player;

import audio.AudioPlayer;
import command.CommandExecutor;
import exceptions.InvalidCommandException;
import playlist.PlaylistLoader;
import playlist.PlaylistManager;
import utils.ConsoleStyle;

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
        this.audioPlayer = new AudioPlayer(playlistManager);
        this.commandExecutor = new CommandExecutor(playlistManager, audioPlayer);

        PlaylistLoader.loadPlaylists(playlistManager);
    }

    /**
     * Runs the whole Music Player
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\uD83C\uDFB5 Welcome to " + ConsoleStyle.bold("KYTE") + " Music Player!");
        System.out.println("Type " + ConsoleStyle.bold("help") + " to see available commands.\n");

        while (true) {
            System.out.print("\uD83C\uDFA7 > ");
            String input = scanner.nextLine().trim();

            try {
                System.out.println();
                String output = commandExecutor.executeCommand(input);
                System.out.println(output);
                if (commandExecutor.shouldExit(input)) break;

            } catch (InvalidCommandException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
