package bookstore.shell;

import bookstore.entity.Author;
import bookstore.entity.Book;
import bookstore.entity.Genre;
import bookstore.services.AuthorService;
import bookstore.services.BookService;
import bookstore.services.GenreService;
import bookstore.services.Impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@ShellComponent
@Transactional
public class BookCommands {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Autowired
    public BookCommands(BookServiceImpl bookService, AuthorService authorService, GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    //To call the command from console type smt like "create-book 1 1 book"

    @ShellMethod("Create book")
    public String createBook(@ShellOption int authorId,
                             @ShellOption int genderId,
                             @ShellOption String bookName) {
        Author author = authorService.findById(authorId);
        Genre genre = genreService.findById(genderId);
        Book book = new Book(author, genre, bookName);
        return bookService.createBook(book).toString();
    }

    @ShellMethod("Find book by name")
    public String findBookByName(@ShellOption String bookName) {
        return bookService.findByName(bookName).toString();
    }

    @ShellMethod("Find book by id")
    public String findBookById(@ShellOption Integer id) {
        return bookService.findById(id).toString();
    }

    @ShellMethod("Delete book by bookName")
    public String deleteBook(@ShellOption String bookName) {
        bookService.deleteByName(bookName);
        return String.format("Book %s is successfully deleted.", bookName);
    }

    @ShellMethod("List all books")
    public void listAllBooks() {
        List<Book> books = bookService.listBooks();
        books.forEach(e -> System.out.println(e.toString()));
    }

    @ShellMethod("Add book's comment")
    public void addComment(@ShellOption int bookId, String comment) {
        bookService.addComment(bookId, comment);
    }

    @ShellMethod("List book's comment")
    public void listCommentsByBook(@ShellOption int bookId) {
        List<String> list = bookService.listCommentByBook(bookId);
        list.forEach(e-> System.out.println(e));
    }
}
