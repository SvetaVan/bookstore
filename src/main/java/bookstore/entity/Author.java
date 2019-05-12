package bookstore.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "authors",
        uniqueConstraints = {@UniqueConstraint(
                columnNames = {"author_id", "author_name"})}
)
@Setter
@Getter
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id", nullable = false, updatable = false)
    private Integer id;

    @Column(name = "author_name", nullable = false, updatable = false)
    private String authorName;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private Collection<Book> books;

    public Author(String author) {
        this.authorName = author;
    }

    public Author(Integer id, Author author) {
        this.id = id;
        this.authorName = author.getAuthorName();
    }

    public Author(Integer id, String authorName) {
        this.id = id;
        this.authorName = authorName;
    }

    public Author(){}

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", authorName='" + authorName + '\'' +
                '}';
    }
}
