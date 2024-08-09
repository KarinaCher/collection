package entity;

public class Book implements Item<String> {

    private String author;
    private String title;
    private String originalAuthor;
    private String originalTitle;
    private String isbn;
    private String note;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalAuthor() {
        return originalAuthor;
    }

    public void setOriginalAuthor(String originalAuthor) {
        this.originalAuthor = originalAuthor;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String getId() {
        return author + title;
    }

    public String getAuthorToSort() {
        if (getOriginalAuthor() == null || getOriginalAuthor().isEmpty()) {
            return getAuthor();
        }
        return getOriginalAuthor();
    }

    public String getIsbnSearch() {
        return isbn.replace("-", "");
    }


}
