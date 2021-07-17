package util;

import entity.Postcard;
import org.jboss.logging.Logger;
import resources.PostcardResource;

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

    public static List<Postcard> readTsv(String file) {
        Map<String, Postcard> list = new HashMap<>();

        URL fileName = PostcardResource.class.getResource(file);

        try (Stream<String> stream = Files.lines(Paths.get(fileName.toURI()))) {
            stream
                    .skip(1)
                    .forEach(line -> {
                        Postcard postcard = helper.parsePostcard(line);
                        if (list.keySet().contains(postcard.getId())) {
                            LOG.warn("Duplicate id " + postcard.getId());
                        }
                        list.put(postcard.getId(), postcard);
                    });
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return list.values().stream()
                .collect(toList());
    }
}
