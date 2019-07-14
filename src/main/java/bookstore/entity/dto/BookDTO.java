package bookstore.entity.dto;

import bookstore.entity.Author;
import bookstore.entity.Genre;

public class BookDTO {

    private Author author;
    private Genre genre;
    private String bookName;

    public BookDTO(String authorName, String genreName, String bookName) {
        this.author = new Author(authorName);
        this.genre = new Genre(genreName);
        this.bookName = bookName;
    }

    public BookDTO(){}

    public Author getAuthor() {
        return author;
    }

    public Genre getGenre() {
        return genre;
    }

    public String getBookName() {
        return bookName;
    }
}
