package util;

import entity.Item;

import java.util.List;
import java.util.Optional;

public class ListUtil<T extends Item<?>, K> {
    public T getById(K id, List<T>... lists) {
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
}
