package util;

import entity.Postcard;
import org.jboss.logging.Logger;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static util.DateUtil.parse;

public class PostcardHelper implements Helper<Postcard> {
    public static final Logger LOG = Logger.getLogger(PostcardHelper.class);

    public static final Comparator<Postcard> BY_DATE = (p1, p2) ->
    {
        LocalDate date2 = p2.getDateReceived() == null ? p2.getDateSent() : p2.getDateReceived();
        LocalDate date1 = p1.getDateReceived() == null ? p1.getDateSent() : p1.getDateReceived();
        return date2.compareTo(date1);
    };

    public static final Comparator<Postcard> BY_ID = Comparator.comparing(Postcard::getId);

    public Postcard parseItem(String line) {
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
            descr = descr.replaceAll("\\[([a-zA-Z\\.\\s\\p{IsAlphabetic}\"]+)\\|([a-zA-Z0-9\\_\\%\\s:/\\.-]+)\\]", "<a href=\"$2\" target=\"_blank\">$1</a>");
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
        return Arrays.stream(field.split(","))
                .map(s -> s.trim())
                .collect(toList());
    }
}
