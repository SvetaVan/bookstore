package bookstore.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;

@Getter
@Setter
public class Genre {

    @Id
    private String id;

    private String genreName;

    public Genre(String genre) {
        this.genreName = genre;
    }

    public Genre(String id, String genre) {
        this.id = id;
        this.genreName = genre;
    }

    public Genre(String id, Genre genre) {
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
