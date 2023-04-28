package util;

import entity.Item;

import java.util.List;
import java.util.Optional;

import static main.OverviewController.ITEMS_PER_PAGE;

public class ListUtil {
    public static <T extends Item<?>, K> T getById(K id, List<T>... lists) {
        for (List<T> list : lists) {
            Optional<T> item = list.stream()
                    .filter(p -> p.getId().equals(id))
                    .findFirst();
            if (item.isPresent()) {
                return item.get();
            }
        }
        return null;
    }

    public static <T extends Item<?>> List<T> getPage(int page, List<T> list) {
        int begin = ITEMS_PER_PAGE * (page - 1);
        int end = ITEMS_PER_PAGE * page;
        end = end > list.size() ? list.size() : end;

        return list.subList(begin, end);
    }
}
