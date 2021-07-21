package util;

import entity.Postcard;
import org.jboss.logging.Logger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
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

        Path path;

        try {
            System.out.println(ClassLoader.getSystemResource("/" + fileName));
            System.out.println(ClassLoader.getSystemResource("/../resources/" + fileName));

            System.out.println(Paths.get(ClassLoader.getSystemResource(fileName).toURI()));
            path = Paths.get(ClassLoader.getSystemResource(fileName).toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }

        try (Stream<String> stream = Files.lines(path)) {
            stream
                    .skip(1)
                    .forEach(line -> {
                        Postcard postcard = helper.parsePostcard(line);
                        if (list.keySet().contains(postcard.getId())) {
                            LOG.warn("Duplicate id " + postcard.getId());
                        }
                        list.put(postcard.getId(), postcard);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list.values().stream()
                .collect(toList());
    }
}
