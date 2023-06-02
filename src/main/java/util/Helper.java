package util;

import entity.Item;

public interface Helper<T extends Item> {
    T parseItem(String line);
}
