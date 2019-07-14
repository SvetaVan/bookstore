package bookstore.entity;

import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Author")
@Setter
public class Author {

    @Id
    @Field
    private String authorId;

    @Field
    private String authorName;

    public Author(String authorName) {
        this.authorName = authorName;
    }

    public Author(String id, Author author) {
        this.authorId = id;
        this.authorName = author.getAuthorName();
    }

    public Author(String id, String authorName) {
        this.authorId = id;
        this.authorName = authorName;
    }

    public Author(){}

    @Override
    public String toString() {
        return "Author{" +
                "author=" + authorId +
                ", authorName='" + authorName + '\'' +
                '}';
    }

    public String getAuthorId() {
        return this.authorId;
    }

    public String getAuthorName() {
        return this.authorName;
    }
}
