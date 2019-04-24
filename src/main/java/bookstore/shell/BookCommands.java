package bookstore.shell;

import bookstore.entity.Book;
import bookstore.services.BookService;
import bookstore.services.Impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;

@ShellComponent
public class BookCommands {

    private final BookService bookService;

    @Autowired
    public BookCommands(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    //To call the command from console type smt like "create-book 1 1 book"

    @ShellMethod("Create book")
    public String createBook(@ShellOption int authorId,
                             @ShellOption int genderId,
                             @ShellOption String bookName) {
        Book book = new Book(authorId, genderId, bookName);
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
}
