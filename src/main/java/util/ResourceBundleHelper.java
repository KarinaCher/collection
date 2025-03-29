package util;

import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ResourceBundleHelper {
    public static Map<String, String> getMap(String bundleName) {
        ResourceBundle bundle = ResourceBundle.getBundle(bundleName);
        return bundle.keySet().stream()
                .collect(Collectors.toMap(k -> k, bundle::getString));
    }
}
