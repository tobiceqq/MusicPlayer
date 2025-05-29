package tests;

import audio.AudioPlayer;
import command.CommandExecutor;
import playlist.PlaylistManager;
import exceptions.InvalidCommandException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class CommandExecutorTest {

    private CommandExecutor executor;

    @BeforeEach
    public void setUp() {
        PlaylistManager playlistManager = new PlaylistManager();
        AudioPlayer audioPlayer = new AudioPlayer(playlistManager);
        executor = new CommandExecutor(playlistManager, audioPlayer);
    }

    @Test
    public void testHelpCommandText() throws InvalidCommandException {
        String result = executor.executeCommand("help");
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    public void testHelpCommandByNumber() throws InvalidCommandException {
        String result = executor.executeCommand("16");
        assertNotNull(result);
        assertFalse(result.isEmpty());
   }

   @Test
   public void testUnknownCommandThrowsException() {
        assertThrows(InvalidCommandException.class, () -> {
            executor.executeCommand("doesn't exist");
        });
   }

   @Test
   public void testShouldExitTrue() {
        assertTrue(executor.shouldExit("exit"));
        assertTrue(executor.shouldExit("17"));
   }

   @Test
   public void testShouldExitFalse() {
        assertFalse(executor.shouldExit("help"));
        assertFalse(executor.shouldExit("16"));
   }
}
