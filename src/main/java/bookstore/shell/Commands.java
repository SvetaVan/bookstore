package bookstore.shell;

import bookstore.entity.Author;
import bookstore.entity.Book;
import bookstore.entity.Genre;
import bookstore.services.AuthorService;
import bookstore.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import bookstore.services.BookService;
import bookstore.services.BookServiceImpl;

import java.util.List;

@ShellComponent
public class Commands {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Autowired
    public Commands(BookServiceImpl bookService, AuthorService authorService, GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    //To call the command from console type smt like "create-book 1 1 book"

    @ShellMethod("Create book")
    public String createBook(@ShellOption int authorId,
                             @ShellOption int genderId,
                             @ShellOption String bookName) {
        Book book = new Book(authorId, genderId, bookName);
        return bookService.createBook(book).toString();
    }

    @ShellMethod("Create author")
    public String createAuthor(@ShellOption String authorName) {
        Author author = new Author(authorName);
        return authorService.createAuthor(author).toString();
    }

    @ShellMethod("Create genre")
    public String createGenre(@ShellOption String genreName) {
        Genre genre = new Genre(genreName);
        return genreService.createGenre(genre).toString();
    }

    @ShellMethod("Find book by name")
    public String findBookByName(@ShellOption String bookName) {
        return bookService.findByName(bookName).toString();
    }

    @ShellMethod("Find author by name")
    public String findAuthorByName(@ShellOption String authorName) {
        return authorService.findByName(authorName).toString();
    }

    @ShellMethod("Find genre by name")
    public String findGenreByName(@ShellOption String genreName) {
        return genreService.findByName(genreName).toString();
    }

    @ShellMethod("Find book by id")
    public String findBookById(@ShellOption Integer id) {
        return bookService.findById(id).toString();
    }

    @ShellMethod("Find author by id")
    public String findAuthorById(@ShellOption Integer id) {
        return authorService.findById(id).toString();
    }

    @ShellMethod("Find genre by id")
    public String findGenreById(@ShellOption Integer id) {
        return genreService.findById(id).toString();
    }

    @ShellMethod("Delete book by bookName")
    public String deleteBook(@ShellOption String bookName) {
        bookService.deleteByName(bookName);
        return String.format("Book %s is successfully deleted.", bookName);
    }

    @ShellMethod("Delete author by name")
    public String deleteAuthor(@ShellOption String authorName) {
        bookService.deleteByName(authorName);
        return String.format("Author %s is successfully deleted.", authorName);
    }

    @ShellMethod("Delete genre by name")
    public String deleteGenre(@ShellOption String genreName) {
        bookService.deleteByName(genreName);
        return String.format("Genre %s is successfully deleted.", genreName);
    }

    @ShellMethod("List all books")
    public void listAllBooks() {
        List<Book> books = bookService.listBooks();
        books.forEach(e -> System.out.println(e.toString()));
    }

    @ShellMethod("List all genres")
    public void listAllGenres() {
        List<Genre> genres = genreService.listAll();
        genres.forEach(e -> System.out.println(e.toString()));
    }

    @ShellMethod("List all authors")
    public void listAllAuthors() {
        List<Author> authors = authorService.listAll();
        authors.forEach(e -> System.out.println(e.toString()));
    }
}
