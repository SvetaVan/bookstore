package bookstore.batchjob;

import bookstore.entity.Book;

public class GenreItem extends Book {

    private String genreId;
    private String genreName;

    public GenreItem(String genreId, String genreName) {
        this.genreId = genreId;
        this.genreName = genreName;
    }

    public String getGenreId() {
        return genreId;
    }

    public String getGenreName() {
        return genreName;
    }
}
