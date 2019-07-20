package bookstore.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;


@Document(collection = "Book")
public class Book {

    @Id
    @Field
    private String id;

    @Field
    @DBRef(lazy = true)
    private Author author;

    @Field
    @DBRef
    private Genre genre;

    @Indexed(unique = true)
    @Field
    private String bookName;

    private List<String> comments;

    public Book(Author author, Genre genre, String bookName) {
        this.author = author;
        this.genre = genre;
        this.bookName = bookName;
    }

    public Book(String id, Book book) {
        this.id = id;
        this.author = book.getAuthor();
        this.genre = book.getGenre();
        this.bookName = book.getBookName();
    }

    public Book(String id, Author author, Genre genre, String bookName) {
        this.id = id;
        this.author = author;
        this.genre = genre;
        this.bookName = bookName;
    }

    public Book(){}

    @Override
    public String toString() {
        return "Book{" +
                "author='" + id + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", bookName='" + bookName + '\'' +
                '}';
    }

    public String getId() {
        return this.id;
    }

    public Author getAuthor() {
        return this.author;
    }

    public Genre getGenre() {
        return this.genre;
    }

    public String getBookName() {
        return this.bookName;
    }

    public List<String> getComments() {
        return this.comments;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }
}
