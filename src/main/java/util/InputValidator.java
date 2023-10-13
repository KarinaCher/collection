package util;

public class InputValidator {
    public static boolean isValidFilter(String filter) {
        return filter != null
                && !filter.isEmpty()
                && filter.matches("^[a-zA-z0-9]+$");
    }
}
