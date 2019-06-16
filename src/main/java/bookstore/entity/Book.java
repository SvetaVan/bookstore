package bookstore.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;


@Document(collection = "Book")
@Getter
@Setter
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
}
