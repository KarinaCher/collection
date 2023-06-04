package resources;

import entity.Book;
import util.BookHelper;

import java.util.ArrayList;
import java.util.List;

import static util.TsvUtil.readTsv;

public class BookResource implements Resource<Book> {

    private static List<Book> list = new ArrayList();

    @Override
    public List<Book> getList() {
        if (list.isEmpty()) {
            BookHelper helper = new BookHelper();
            list.addAll(readTsv("Library.tsv", helper));
        }
        return list;
    }

    @Override
    public boolean isBelong(String tagName, Book postcard) {
        return true;
    }
}
