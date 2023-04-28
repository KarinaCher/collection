package util;

import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateUtil {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);

    public static LocalDate parse(String value) {
        return StringUtils.isEmpty(value) ? null : LocalDate.parse(value, formatter);
    }

    public static String format(LocalDate date) {
        return date == null ? "" : date.format(formatter);
    }
}
