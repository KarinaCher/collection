package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InputValidator {
    private static final Logger log = LoggerFactory.getLogger(InputValidator.class);

    public static boolean isValidFilter(String filter) {
        return filter != null
                && !filter.isEmpty()
                && filter.matches("^[a-zA-z0-9]+$");
    }

}
