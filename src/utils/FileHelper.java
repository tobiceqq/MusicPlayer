package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Utility class for reading and writing files.
 */
public class FileHelper {

    /**
     * Reads the content of a text file as a single string.
     *
     * @param filePath the path to the file
     * @return the file content as a string
     * @throws IOException if an I/O error occurs
     */
    public static String readFileAsString(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        return Files.readString(path);
    }

    /**
     * Reads the content of a text file as a list of lines.
     *
     * @param filePath the path to the file
     * @return a list containing all lines from the file
     * @throws IOException if an I/O error occurs
     */
    public static List<String> readFileAsLines(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        return Files.readAllLines(path);
    }

    /**
     * Writes the given content to a text file.
     *
     * @param filePath the path to the file
     * @param content the content to write
     * @throws IOException if an I/O error occurs
     */
    public static void writeFile(String filePath, String content) throws IOException {
        Path path = Paths.get(filePath);
        Files.writeString(path, content);
    }

    /**
     * Writes the styled help menu to a text file.
     *
     * @param filePath the path to the help file
     */
    public static void saveHelpTextToFile(String filePath) {
        StringBuilder sb = new StringBuilder();
        sb.append("\uD83C\uDFB5 Available commands:\n\n");

        sb.append("⏯\uFE0F ").append(ConsoleStyle.bold("play")).append("Start playing the current song\n");
        sb.append("⏸\uFE0F ").append(ConsoleStyle.bold("pause")).append("Pause the currently playing song\n");
        sb.append("⏭\uFE0F ").append(ConsoleStyle.bold("next")).append("Play the next song in the playlist\n");
        sb.append("⏮\uFE0F ").append(ConsoleStyle.bold("previous")).append("Play the previous song in the playlist\n");
        sb.append("\uD83D\uDD00 ").append(ConsoleStyle.bold("shuffle")).append("Play songs in random order\n");
        sb.append("\uD83D\uDD01 ").append(ConsoleStyle.bold("loop")).append("Toggle looping mode (one song or whole playlist)\n");
        sb.append("⏩ ").append(ConsoleStyle.bold("seekForward")).append("Seek forward in the current song\n");
        sb.append("⏪ ").append(ConsoleStyle.bold("seekBackward")).append("Seek backward in the current song\n");

        try {
            writeFile(filePath, sb.toString());
        } catch (IOException e) {
            System.err.println(ConsoleStyle.color("❌ Error while reading help file: " + e.getMessage(), ConsoleStyle.RED));
        }
    }

    /**
     * Reads a file with ANSI-styled content and prints it to the console.
     * @param filePath the path to the file to read and print
     */
    public static void printFileWithAnsi(String filePath) {
        try {
            List<String> lines = readFileAsLines(filePath);
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println(ConsoleStyle.color("❌ Error while reading file: " + e.getMessage(), ConsoleStyle.RED));
        }
    }
}
