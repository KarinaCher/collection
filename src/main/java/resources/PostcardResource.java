package resources;

import entity.Postcard;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.LinkedList;

import static util.PostcardHelper.BY_DATE;
import static util.TsvUtil.readTsv;

@Component
public class PostcardResource implements Resource<Postcard> {

    private static LinkedList<Postcard> postcardList = new LinkedList<>();

    public LinkedList<Postcard> getList() {
        if (postcardList.isEmpty()) {
            postcardList.addAll(readTsv("Postcard collection - 1980x.tsv"));
            postcardList.addAll(readTsv("Postcard collection - 1990x.tsv"));
            postcardList.addAll(readTsv("Postcard collection - 2000x.tsv"));
            postcardList.addAll(readTsv("Postcard collection - 2010x.tsv"));
            int currentYear = LocalDate.now().getYear();
            for (int i = 2019; i <= currentYear; i++) {
                postcardList.addAll(readTsv("Postcard collection - " + i + ".tsv"));
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
