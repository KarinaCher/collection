package util;

import entity.Postcard;
import org.jboss.logging.Logger;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
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

        URL res = TsvUtil.class.getClassLoader().getResource(fileName);
        File file = null;
        try {
            file = Paths.get(res.toURI()).toFile();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        String absolutePath = file.getAbsolutePath();

        try (Stream<String> stream = Files.lines(Paths.get(absolutePath))) {
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
