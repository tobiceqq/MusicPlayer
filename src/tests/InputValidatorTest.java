package tests;

import org.junit.jupiter.api.Test;
import utils.InputValidator;

import static org.junit.jupiter.api.Assertions.*;

public class InputValidatorTest {

    @Test
    public void testIsNullOrEmpty() {
        assertTrue(InputValidator.isNullOrEmpty(null), "Null should be considered empty");
        assertTrue(InputValidator.isNullOrEmpty(""), "Empty string should be considered empty");
        assertTrue(InputValidator.isNullOrEmpty("   "), "Whitespace should be considered empty");
        assertFalse(InputValidator.isNullOrEmpty("Hello"), "Non-empty string should not be empty");
    }

    @Test
    public void testIsValidInteger() {
        assertTrue(InputValidator.isValidInteger("123"), "Valid integer string should pass");
        assertTrue(InputValidator.isValidInteger("-456"), "Valid negative integer string should pass");
        assertFalse(InputValidator.isValidInteger("abc"), "Non-integer string should fail");
        assertFalse(InputValidator.isValidInteger("12.3"), "Decimal number should fail");
        assertFalse(InputValidator.isValidInteger(""), "Empty string should fail");
    }

    @Test
    public void testMatchesRegex() {
        String regex = "\\d{3}";

        assertTrue(InputValidator.matchesRegex("123", regex), "String '123' should match");
        assertFalse(InputValidator.matchesRegex("12", regex), "String '12' should not match");
        assertFalse(InputValidator.matchesRegex("abcd", regex), "String 'abcd' should not match");
        assertFalse(InputValidator.matchesRegex("", regex), "Empty string should not match");
    }

}