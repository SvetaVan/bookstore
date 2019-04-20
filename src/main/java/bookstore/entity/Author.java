package bookstore.entity;

import java.util.Objects;

public class Author {

    final private Integer id;
    final private String authorName;

    public Author(String author) {
        this.id = null;
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

    public String getAuthorName() {
        return authorName;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author1 = (Author) o;
        return id.equals(author1.id) &&
                Objects.equals(authorName, author1.authorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authorName);
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", authorName='" + authorName + '\'' +
                '}';
    }
}
