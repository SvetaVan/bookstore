package bookstore.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
@Table(name = "genres",
        uniqueConstraints = {@UniqueConstraint(
                columnNames = {"genre_id", "genre_name"})}
)
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id", nullable = false, updatable = false)
    private Integer id;

    @Column(name = "genre_name", nullable = false, updatable = false)
    private String genreName;

    @OneToMany(mappedBy = "genre", fetch = FetchType.LAZY)
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
