package bookstore.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.Collection;

@Document
@Getter
@Setter
public class Genre {

    @Id
    private Integer id;

    private String genreName;

    private Collection<Book> books;

    public Genre(String genre) {
        this.genreName = genre;
    }

    public Genre(Integer id, String genre) {
        this.id = id;
        this.genreName = genre;
    }

    public Genre(Integer id, Genre genre) {
        this.id = id;
        this.genreName = genre.getGenreName();
    }

    public Genre(){}

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", genreName='" + genreName + '\'' +
                '}';
    }
}
