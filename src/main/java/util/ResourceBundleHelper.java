package util;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ResourceBundleHelper {

    public static final String[] SENDERS_INCLUDE = new String[]{"I", "Aliona"};

    public static Map<String, String> getMap(String bundleName) {
        ResourceBundle bundle = ResourceBundle.getBundle(bundleName);
        return bundle.keySet().stream()
                .collect(Collectors.toMap(k -> k, bundle::getString));
    }

    public static Map<String, String> getMap(String bundleName, String[] include) {
        ResourceBundle bundle = ResourceBundle.getBundle(bundleName);
        List<String> includeList = Arrays.stream(include).collect(Collectors.toList());
        return bundle.keySet().stream()
                .filter(k -> includeList.contains(k))
                .collect(Collectors.toMap(k -> k, bundle::getString));
    }
}
