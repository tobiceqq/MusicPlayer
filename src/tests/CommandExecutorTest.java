package tests;

import audio.AudioPlayer;
import command.CommandExecutor;
import playlist.PlaylistManager;
import exceptions.InvalidCommandException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class CommandExecutorTest {

    private CommandExecutor executor;

    @BeforeEach
    void setUp() {
        PlaylistManager playlistManager = new PlaylistManager();
        AudioPlayer audioPlayer = new AudioPlayer(playlistManager);
        executor = new CommandExecutor(playlistManager, audioPlayer);
    }

    @Test
    void testHelpCommandText() throws InvalidCommandException {
        String result = executor.executeCommand("help");
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    void testHelpCommandByNumber() throws InvalidCommandException {
        String result = executor.executeCommand("16");
        assertNotNull(result);
        assertFalse(result.isEmpty());
   }

   @Test
    void testUnknownCommandThrowsException() {
        assertThrows(InvalidCommandException.class, () -> {
            executor.executeCommand("doesn't exist");
        });
   }

   @Test
    void testShouldExitTrue() {
        assertTrue(executor.shouldExit("exit"));
        assertTrue(executor.shouldExit("17"));
   }

   @Test
    void testShouldExitFalse() {
        assertFalse(executor.shouldExit("help"));
        assertFalse(executor.shouldExit("16"));
   }
}
