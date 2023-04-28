package resources;

import entity.Postcard;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static util.PostcardHelper.BY_ID;
import static util.TsvUtil.readTsv;

@Component
public class PostcardOtherResource implements Resource<Postcard> {
    private static List<Postcard> postcardList = new ArrayList<>();

    @Override
    public List<Postcard> getList() {
        if (postcardList.isEmpty()) {
            postcardList.addAll(readTsv("Postcard collection - other.tsv"));
            postcardList.forEach(postcard -> postcard.setMine(false));
            postcardList.sort(BY_ID);
        }
        return postcardList;
    }

    @Override
    public List<Postcard> getListWithTag(String tagName, Comparator<Postcard> comparator) {
        return null;
    }

    @Override
    public boolean isBelong(String tagName, Postcard postcard) {
        return false;
    }
}
