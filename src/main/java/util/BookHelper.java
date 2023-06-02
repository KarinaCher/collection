package util;

import entity.Book;

public class BookHelper implements Helper<Book>{
    @Override
    public Book parseItem(String line) {
        String[] data = line.split("\t");

        String author = data[0];
        String title = data[1];

        Book book = new Book();
        book.setAuthor(author);
        book.setTitle(title);
        if (data.length > 2) {
            book.setOriginalAuthor(data[2]);
        }
        if (data.length > 3) {
            book.setOriginalTitle(data[3]);
        }

        return book;
    }
}
