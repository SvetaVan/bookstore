package bookstore.entity;

import java.util.Objects;

public class Genre {

    final private Integer id;
    final private String genreName;

    public Genre(String genre){
        this.id = null;
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

    public String getGenreName() {
        return genreName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre1 = (Genre) o;
        return Objects.equals(genreName, genre1.genreName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genreName);
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", genreName='" + genreName + '\'' +
                '}';
    }
}
