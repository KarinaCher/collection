package util;

import entity.Item;
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

    public static <T extends Item> List<T> readTsv(String fileName, Helper helper) {
        Map<Object, T> list = new HashMap<>();

        Stream<String> stream = new BufferedReader(
                new InputStreamReader(
                        TsvUtil.class.getResourceAsStream("/" + fileName))).lines();
        stream
                .skip(1)
                .forEach(line -> {
                    T postcard = (T) helper.parseItem(line);
                    if (list.keySet().contains(postcard.getId())) {
                        LOG.warn("Duplicate id " + postcard.getId());
                    }
                    list.put(postcard.getId(), postcard);
                });
        return list.values().stream()
                .collect(toList());
    }
}
