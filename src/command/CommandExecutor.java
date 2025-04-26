package command;

import exceptions.InvalidCommandException;

import java.util.HashMap;
import java.util.Map;

/**
 * Executes commands based on user input.
 */
public class CommandExecutor {

    private final Map<String, Command> commandMap;

    public CommandExecutor() {
        commandMap = new HashMap<>();
        registerCommands();
    }

    /**
     * Registers all available commands to the command map.
     */
    private void registerCommands() {

        commandMap.put("play" , new Play());
        commandMap.put("1" , new Play());

        commandMap.put();
        commandMap.put();

        commandMap.put();
        commandMap.put();

        commandMap.put();
        commandMap.put();

        commandMap.put();
        commandMap.put();

        commandMap.put();
        commandMap.put();

        commandMap.put();
        commandMap.put();

        commandMap.put();
        commandMap.put();

        commandMap.put();
        commandMap.put();

        commandMap.put();
        commandMap.put();

        commandMap.put();
        commandMap.put();

        commandMap.put();
        commandMap.put();

        commandMap.put();
        commandMap.put();

        commandMap.put();
        commandMap.put();

        commandMap.put();
        commandMap.put();

        commandMap.put();
        commandMap.put();

        commandMap.put();
        commandMap.put();
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
