package bookstore.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "books",
        uniqueConstraints = {@UniqueConstraint(
                columnNames = {"book_id", "author_id", "genre_id"})}
)
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id", nullable = false, updatable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne(optional = false)
    @JoinColumn(name = "genre_id")
    private Genre genre;


    @Column(name = "book_name", nullable = false, updatable = false)
    private String bookName;

    @ElementCollection
    @CollectionTable(
            name = "comment",
            joinColumns = @JoinColumn(name = "book_id")
    )
    private List<String> comments;

    public Book(Author author, Genre genre, String bookName) {
        this.author = author;
        this.genre = genre;
        this.bookName = bookName;
    }

    public Book(Integer id, Book book) {
        this.id = id;
        this.author = book.getAuthor();
        this.genre = book.getGenre();
        this.bookName = book.getBookName();
    }

    public Book(Integer id, Author author, Genre genre, String bookName) {
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
