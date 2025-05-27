package command;

import exceptions.InvalidCommandException;
import audio.AudioPlayer;
import model.Playlist;
import playlist.PlaylistManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Executes commands based on user input.
 */
public class CommandExecutor {

    private final Map<String, Command> commandMap;
    private final PlaylistManager playlistManager;
    private final AudioPlayer audioPlayer;
    private final Scanner scanner;

    public CommandExecutor(PlaylistManager playlistManager, AudioPlayer audioPlayer) {
        this.commandMap = new HashMap<>();
        this.playlistManager = playlistManager;
        this.audioPlayer = audioPlayer;
        this.scanner = new Scanner(System.in);
        registerCommands();
    }

    /**
     * Registers all available commands to the command map.
     */
    private void registerCommands() {

        commandMap.put("play", new Play(playlistManager, audioPlayer));
        commandMap.put("1", new Play(playlistManager, audioPlayer));

        commandMap.put("pause", new Pause(playlistManager, audioPlayer));
        commandMap.put("2", new Pause(playlistManager, audioPlayer));

        commandMap.put("next", new Next(playlistManager, audioPlayer));
        commandMap.put("3", new Next(playlistManager, audioPlayer));

        commandMap.put("previous", new Previous(playlistManager, audioPlayer));
        commandMap.put("4", new Previous(playlistManager, audioPlayer));

        commandMap.put("addsong", new AddSong(playlistManager, scanner));
        commandMap.put("5", new AddSong(playlistManager, scanner));

        commandMap.put("removesong", new RemoveSong(playlistManager, scanner));
        commandMap.put("6", new RemoveSong(playlistManager, scanner));

        commandMap.put("search", new Search(playlistManager, scanner));
        commandMap.put("7", new Search(playlistManager, scanner));

        commandMap.put("shuffle", new Shuffle(playlistManager, audioPlayer));
        commandMap.put("8", new Shuffle(playlistManager, audioPlayer));

        commandMap.put("favorite", new Favorite(playlistManager, scanner));
        commandMap.put("9", new Favorite(playlistManager, scanner));

        commandMap.put("loop", new Loop(playlistManager, scanner));
        commandMap.put("10", new Loop(playlistManager, scanner));

        commandMap.put("normalmode", new NormalMode(playlistManager));
        commandMap.put("11", new NormalMode(playlistManager));

        commandMap.put("seekforward", new SeekForward(playlistManager , audioPlayer , 15l));
        commandMap.put("12", new SeekForward(playlistManager , audioPlayer , 15l));

        commandMap.put("seekbackward", new SeekBackward(playlistManager , audioPlayer , 15l));
        commandMap.put("13", new SeekBackward(playlistManager , audioPlayer , 15l));

        commandMap.put("showcurrentsong", new ShowCurrentSong(playlistManager));
        commandMap.put("14", new ShowCurrentSong(playlistManager));

        commandMap.put("stats", new Stats(playlistManager));
        commandMap.put("15", new Stats(playlistManager));

        commandMap.put("help", new Help());
        commandMap.put("16", new Help());

        commandMap.put("exit", new Exit());
        commandMap.put("17", new Exit());

        commandMap.put("select", new SelectPlaylist(playlistManager, scanner));
        commandMap.put("18", new SelectPlaylist(playlistManager, scanner));


    }

    /**
     * Executes the command matching the given input.
     *
     * @param input the user input
     * @return the result message of the executed command
     * @throws InvalidCommandException if the input command is invalid
     */
    public String executeCommand(String input) throws InvalidCommandException {
        Command command = commandMap.get(input.toLowerCase());
        if (command == null) {
            throw new InvalidCommandException("❓ Unknown command: " + input);
        }
        return command.execute();
    }

    /**
     * Determines if the given command  should terminate the app.
     *
     * @param input the user input
     * @return true if the command should exit the app, false otherwise
     */
    public boolean shouldExit(String input) {
        Command command = commandMap.get(input.toLowerCase());
        return command != null && command.exit();
    }
}
