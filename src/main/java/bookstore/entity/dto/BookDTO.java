package bookstore.entity.dto;

public class BookDTO {

    private String authorName;
    private String genreName;
    private String bookName;

    public BookDTO(String authorName, String genreName, String bookName) {
        this.authorName = authorName;
        this.genreName = genreName;
        this.bookName = bookName;
    }

    public BookDTO(){}

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}
