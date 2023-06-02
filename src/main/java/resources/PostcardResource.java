package resources;

import entity.Postcard;
import org.springframework.stereotype.Component;
import util.PostcardHelper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static util.PostcardHelper.BY_DATE;
import static util.TsvUtil.readTsv;

@Component
public class PostcardResource implements Resource<Postcard> {

    private static List<Postcard> postcardList = new ArrayList();

    public List<Postcard> getList() {
        if (postcardList.isEmpty()) {
            PostcardHelper helper = new PostcardHelper();
            postcardList.addAll(readTsv("Postcard collection - 1980x.tsv", helper));
            postcardList.addAll(readTsv("Postcard collection - 1990x.tsv", helper));
            postcardList.addAll(readTsv("Postcard collection - 2000x.tsv", helper));
            postcardList.addAll(readTsv("Postcard collection - 2010x.tsv", helper));
            int currentYear = LocalDate.now().getYear();
            for (int i = 2019; i <= currentYear; i++) {
                postcardList.addAll(readTsv("Postcard collection - " + i + ".tsv", helper));
            }
            postcardList.sort(BY_DATE);
        }
        return postcardList;
    }

    public boolean isBelong(String tagName, Postcard postcard) {
        return postcard.getCountry().equals(tagName)
               || (postcard.getCity() != null && postcard.getCity().equals(tagName))
               || postcard.getSenders().contains(tagName)
               || postcard.getTags().contains(tagName)
               || postcard.getYear().equals(tagName);
    }
}
