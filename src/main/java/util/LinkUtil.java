package util;

public class LinkUtil {

    public static String formatLink(String link) {
        return link
                .replaceAll("\\[([0-9]{8}[A-Z]{2}[0-9]?)\\|(\\p{L}+)\\]", "<a href=\"/postcard/$1\">$2</a>")
                .replaceAll("\\[([a-zA-Z0-9\\.\\s\\p{IsAlphabetic}-\"]+)\\|([a-zA-Z0-9\\_\\%\\s:/\\.\\(\\)-]+)\\]", "<a href=\"$2\" target=\"_blank\">$1</a>");
    }
}
