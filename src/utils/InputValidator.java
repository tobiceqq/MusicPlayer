package utils;

/**
 * Utility class for validating user input.
 */
public class InputValidator {

    /**
     * Checks if the input string is null or empty.
     *
     * @param input the input string
     * @return true if the input is null or empty, false otherwise
     */
    public static boolean isNullOrEmpty(String input) {
        return input == null || input.trim().isEmpty();
    }

    /**
     * Checks if the input string is a valid integer number.
     *
     * @param input the input string
     * @return true if the input is a valid integer, false otherwise
     */
    public static boolean isValidInteger(String input) {
        if (isNullOrEmpty(input)) {
            return false;
        }
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks if the input string matches a given regular expression pattern.
     *
     * @param input the input string
     * @param regex the regular expression to match
     * @return true if the input matches the regex, false otherwise
     */
    public static boolean matchesRegex(String input, String regex) {
        if (isNullOrEmpty(input)) {
            return false;
        }
        return input.matches(regex);
    }
}
