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
    private Author authorId;

    @Field
    @DBRef
    private Genre genreId;

    @Indexed(unique = true)
    @Field
    private String bookName;

    private List<String> comments;

    public Book(Author authorId, Genre genreId, String bookName) {
        this.authorId = authorId;
        this.genreId = genreId;
        this.bookName = bookName;
    }

    public Book(String id, Book book) {
        this.id = id;
        this.authorId = book.getAuthorId();
        this.genreId = book.getGenreId();
        this.bookName = book.getBookName();
    }

    public Book(String id, Author authorId, Genre genreId, String bookName) {
        this.id = id;
        this.authorId = authorId;
        this.genreId = genreId;
        this.bookName = bookName;
    }

    public Book(){}

    @Override
    public String toString() {
        return "Book{" +
                "authorId='" + id + '\'' +
                ", authorId='" + authorId + '\'' +
                ", genreId='" + genreId + '\'' +
                ", bookName='" + bookName + '\'' +
                '}';
    }
}
