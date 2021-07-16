package resources;

import entity.Postcard;
import org.jboss.logging.Logger;
import util.ListUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static main.OverviewController.ITEMS_PER_PAGE;
import static util.DateUtil.parse;

public class PostcardResource {
    public static final Logger LOG = Logger.getLogger(PostcardResource.class);
    private static List<Postcard> postcardList = new ArrayList<>();
    private static List<Postcard> postcardListOther = new ArrayList<>();

    private static final Comparator<Postcard> BY_DATE = (p1, p2) ->
    {
        LocalDate date2 = p2.getDateReceived() == null ? p2.getDateSent() : p2.getDateReceived();
        LocalDate date1 = p1.getDateReceived() == null ? p1.getDateSent() : p1.getDateReceived();
        return date2.compareTo(date1);
    };

    private static final Comparator<Postcard> BY_ID = (p1, p2) ->
            p2.getId().compareTo(p1.getId());

    public static List<Postcard> getList() {
        if (postcardList.isEmpty()) {
            postcardList.addAll(readTsv("/Postcard collection - 1980x.tsv"));
            postcardList.addAll(readTsv("/Postcard collection - 1990x.tsv"));
            postcardList.addAll(readTsv("/Postcard collection - 2000x.tsv"));
            postcardList.addAll(readTsv("/Postcard collection - 2010x.tsv"));
            int currentYear = LocalDate.now().getYear();
            for (int i = 2019; i <= currentYear; i++) {
                postcardList.addAll(readTsv("/Postcard collection - " + i + ".tsv"));
            }
            postcardList.sort(BY_DATE);
        }
        return postcardList;
    }

    public static List<Postcard> getOtherList() {
        if (postcardListOther.isEmpty()) {
            postcardListOther.addAll(readTsv("/Postcard collection - other.tsv"));
            postcardListOther.forEach(postcard -> postcard.setMine(false));
            postcardListOther.sort(BY_ID);
        }
        return postcardListOther;
    }

    public static int getFullCount() {
        return getList().size();
    }

    public static int getOthersFullCount() {
        return getOtherList().size();
    }

    public static List<Postcard> getList(int page, boolean isOther) {
        return getPage(page, isOther ? getOtherList() : getList());
    }

    public static List<Postcard> getList(int page) {
        return getPage(page, getList());
    }

    public static List<Postcard> getPage(int page, List<Postcard> list) {
        int begin = ITEMS_PER_PAGE * (page - 1);
        int end = ITEMS_PER_PAGE * page;
        end = end > list.size() ? list.size() : end;

        return list.subList(begin, end);
    }

    public static Postcard getById(String id) {
        return (new ListUtil<Postcard, String>())
                .getById(id, getList(), getOtherList());
    }

    public static List<Postcard> getListWithTag(String tagName) {
        if (tagName.isEmpty()) {
            return getList();
        }

        return getList().stream()
                .filter(postcard -> isBelong(tagName, postcard))
                .sorted(BY_DATE)
                .collect(toList());
    }

    private static boolean isBelong(String tagName, Postcard postcard) {
        return postcard.getCountry().equals(tagName)
                || (postcard.getCity() != null && postcard.getCity().equals(tagName))
                || postcard.getSenders().contains(tagName)
                || postcard.getTags().contains(tagName)
                || postcard.getYear().equals(tagName);
    }

    static List<Postcard> readTsv(String file) {
        Map<String, Postcard> list = new HashMap<>();

        URL fileName = PostcardResource.class.getResource(file);

        try (Stream<String> stream = Files.lines(Paths.get(fileName.toURI()))) {
            stream
                    .skip(1)
                    .forEach(line -> {
                        Postcard postcard = parsePostcard(line);
                        if (list.keySet().contains(postcard.getId())) {
                            LOG.warn("Dublicate id " + postcard.getId());
                        }
                        list.put(postcard.getId(), postcard);
                    });
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return list.values().stream()
                .collect(toList());
    }

    private static Postcard parsePostcard(String line) {
        String[] data = line.split("\t");

        String id = data[0];
        String image = data[1];
        String dateSent = data[2];
        String dateReceived = data[3];
        String height = data[4];
        String width = data[5];
        String country = data[6];
        String city = data[7];
        String senderList = data[8];

        Postcard postcard = new Postcard();
        postcard.setId(id);
        postcard.getImages().add(image);
        postcard.setDateSent(parse(dateSent));
        postcard.setDateReceived(parse(dateReceived));

        if (dateReceived.isEmpty()) {
            LOG.warn("Date received is empty. ID = " + postcard.getId());
        }

        if (height.isEmpty() || width.isEmpty()) {
            postcard.setHeight(0);
            postcard.setWidth(0);
        } else {
            postcard.setHeight(Integer.parseInt(height));
            postcard.setWidth(Integer.parseInt(width));
        }

        postcard.setCountry(country);
        postcard.setCity(city);
        postcard.setSenders(readMultiValues(senderList));

        if (data.length > 9) {
            String descr = data[9];
            descr = descr.replaceAll("\\[([0-9]{8}[A-Z]{2}[0-9]?)\\|(\\p{L}+)\\]", "<a href=\"/postcard/$1\">$2</a>");
            descr = descr.replaceAll("\\[([a-zA-Z\\.\\s\\p{IsAlphabetic}]+)\\|([a-zA-Z\\s:/\\.-]+)\\]", "<a href=\"$2\" target=\"_blank\">$1</a>");
            postcard.setDescription(descr);
        }
        if (data.length > 10) {
            postcard.setTags(readMultiValues(data[10]));
        }
        if (data.length > 11 && !data[11].isEmpty()) {
            int postcardCount = Integer.parseInt(data[11]);
            for (int i = 2; i <= postcardCount; i++) {
                postcard.getImages().add(image.replaceAll("1.jpg", i + ".jpg"));
            }
        }
        return postcard;
    }

    private static List<String> readMultiValues(String field) {
        if (field == null || field.isEmpty()) {
            return Collections.emptyList();
        }
        return Arrays.stream(field.split(", "))
                .collect(toList());
    }
}
