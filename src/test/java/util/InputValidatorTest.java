package util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputValidatorTest {

    @Test
    void isValidFilter() {
        assertTrue(InputValidator.isValidFilter("birthday"));
        assertTrue(InputValidator.isValidFilter("3D"));

        assertFalse(InputValidator.isValidFilter("-tag"));
        assertFalse(InputValidator.isValidFilter(".tag"));
        assertFalse(InputValidator.isValidFilter("123.tag"));
    }
}