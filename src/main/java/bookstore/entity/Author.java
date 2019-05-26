package bookstore.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;

@Document
@Setter
@Getter
public class Author {

    @Id
    private BigInteger id;

    private String authorName;

    private Collection<Book> books;

    public Author(String authorName) {
        this.authorName = authorName;
        books = new ArrayList<>();
    }

    public Author(BigInteger id, Author author) {
        this.id = id;
        this.authorName = author.getAuthorName();
    }

    public Author(BigInteger id, String authorName) {
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
