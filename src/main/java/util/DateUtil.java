package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateUtil
{
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
    
    public static LocalDate parse(String value) {
        return LocalDate.parse(value, formatter);
    }
    
    public static String format(LocalDate date) {
        return date.format(formatter);
    }
}
