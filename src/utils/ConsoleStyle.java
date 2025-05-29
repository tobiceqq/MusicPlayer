package utils;

/**
 * Provides ANSI color and styles codes for styled console output.
 *
 * @author https://www.youtube.com/watch?v=MERwLlyIJvs
 */
public class ConsoleStyle {

    // Reset
    public static final String RESET = "\u001B[0m";

    // Styles
    public static final String BOLD = "\u001B[1m";
    public static final String ITALIC = "\u001B[3m";
    public static final String UNDERLINE = "\u001B[4m";

    // Text colors
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    // Background colors
    public static final String BG_BLACK = "\u001B[40m";
    public static final String BG_RED = "\u001B[41m";
    public static final String BG_GREEN = "\u001B[42m";
    public static final String BG_YELLOW = "\u001B[43m";
    public static final String BG_BLUE = "\u001B[44m";
    public static final String BG_PURPLE = "\u001B[45m";
    public static final String BG_CYAN = "\u001B[46m";
    public static final String BG_WHITE = "\u001B[47m";

    /**
     * Methods for being able to use the console style.
     */
    public static String bold(String text) {
        return BOLD + text + RESET;
    }

    public static String italic(String text) {
        return ITALIC + text + RESET;
    }

    public static String underline(String text) {
        return UNDERLINE + text + RESET;
    }

    public static String color(String text, String color) {
        return color + text + RESET;
    }

    public static String background(String text, String backgroundColor) {
        return backgroundColor + text + RESET;
    }
}
