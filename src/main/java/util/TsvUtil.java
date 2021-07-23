package util;

import entity.Postcard;
import org.jboss.logging.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class TsvUtil {
    public static final Logger LOG = Logger.getLogger(TsvUtil.class);
    private static final PostcardHelper helper = new PostcardHelper();

    public static List<Postcard> readTsv(String fileName) {
        Map<String, Postcard> list = new HashMap<>();

        Stream<String> stream = new BufferedReader(
                new InputStreamReader(
                        TsvUtil.class.getResourceAsStream("/" + fileName))).lines();
        stream
                .skip(1)
                .forEach(line -> {
                    Postcard postcard = helper.parsePostcard(line);
                    if (list.keySet().contains(postcard.getId())) {
                        LOG.warn("Duplicate id " + postcard.getId());
                    }
                    list.put(postcard.getId(), postcard);
                });
        return list.values().stream()
                .collect(toList());
    }
}
