package bookstore.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.util.List;

@Document(collection = "Book")
@Getter
@Setter
public class Book {

    @Id
    private String id;

    @Field(value = "author")
    private Author author;

    @Field(value = "genre")
    private Genre genre;

    @Indexed(unique = true)
    @Field(value = "book_name")
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
                "id=" + id +
                ", author=" + author +
                ", genre=" + genre +
                ", bookName='" + bookName + '\'' +
                '}';
    }
}
