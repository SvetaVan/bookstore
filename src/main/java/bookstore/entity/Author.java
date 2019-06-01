package bookstore.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Setter
@Getter
public class Author {

    @Id
    private String id;

    private String authorName;

    public Author(String authorName) {
        this.authorName = authorName;
    }

    public Author(String id, Author author) {
        this.id = id;
        this.authorName = author.getAuthorName();
    }

    public Author(String id, String authorName) {
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
