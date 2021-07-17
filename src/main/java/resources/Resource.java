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

    default List<T> getListWithTag(String tagName, Comparator<T> comparator) {
        if (tagName.isEmpty()) {
            return getList();
        }

        return getList().stream()
                .filter(postcard -> isBelong(tagName, postcard))
                .sorted(comparator)
                .collect(toList());
    }

    boolean isBelong(String tagName, T postcard);
}
