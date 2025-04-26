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
}
