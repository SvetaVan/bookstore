package bookstore.entity;

import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Genre")
@Setter
public class Genre {

    @Id
    @Field
    private String genreId;

    @Field
    private String genreName;

    public Genre(String genre) {
        this.genreName = genre;
    }

    public Genre(String id, String genre) {
        this.genreId = id;
        this.genreName = genre;
    }

    public Genre(String id, Genre genre) {
        this.genreId = id;
        this.genreName = genre.getGenreName();
    }

    public Genre(){}

    @Override
    public String toString() {
        return "Genre{" +
                "genre=" + genreId +
                ", genreName='" + genreName + '\'' +
                '}';
    }

    public String getGenreId() {
        return this.genreId;
    }

    public String getGenreName() {
        return this.genreName;
    }
}
