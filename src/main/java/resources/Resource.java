package resources;

import entity.Item;

import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

public interface Resource<T extends Item> {

    List<T> getList();

    default int getCount() {
        return getList().size();
    }

    default List<T> getListWithTag(Comparator<T> comparator, String... tagName) {
        if (tagName.length == 0) {
            return getList();
        }

        return getList().stream()
                .filter(postcard -> isBelongList(tagName, postcard))
                .sorted(comparator)
                .collect(toList());
    }

    private boolean isBelongList(String[] tagName, T postcard) {
        boolean belong = true;
        for (String tag : tagName) {
            belong &= isBelong(tag, postcard);
        }

        return belong;
    }

    boolean isBelong(String tagName, T postcard);
}
