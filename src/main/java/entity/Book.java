package entity;

public class Book implements Item<String> {

    private String author;
    private String title;
    private String originalAuthor;
    private String originalTitle;

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
}
