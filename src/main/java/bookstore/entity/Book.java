package bookstore.entity;

import java.util.Objects;

public class Book {

    final private Integer id;
    final private int authorId;
    final private int genreId;
    final private String bookName;

    public Book(int authorId, int genreId, String bookName) {
        this.id = null;
        this.authorId = authorId;
        this.genreId = genreId;
        this.bookName = bookName;
    }

    public Book(Integer id, Book book) {
        this.id = id;
        this.authorId = book.getAuthorId();
        this.genreId = book.getGenreId();
        this.bookName = book.getBookName();
    }

    public Book(Integer id, int authorId, int genreId, String bookName) {
        this.id = id;
        this.authorId = authorId;
        this.genreId = genreId;
        this.bookName = bookName;
    }

    public Integer getId() {
        return id;
    }

    public int getAuthorId() {
        return authorId;
    }

    public int getGenreId() {
        return genreId;
    }

    public String getBookName() {
        return bookName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return authorId == book.authorId &&
                genreId == book.genreId &&
                Objects.equals(id, book.id) &&
                Objects.equals(bookName, book.bookName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authorId, genreId, bookName);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", authorId=" + authorId +
                ", genreId=" + genreId +
                ", bookName='" + bookName + '\'' +
                '}';
    }
}
