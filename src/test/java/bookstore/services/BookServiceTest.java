package bookstore.services;

import bookstore.entity.Author;
import bookstore.entity.Book;
import bookstore.entity.Genre;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"})
@RunWith(SpringRunner.class)
public class BookServiceTest {
    @Autowired
    private BookService bookService;
    @Autowired
    private GenreService genreService;
    @Autowired
    private AuthorService authorService;


    @Test
    public void BookCreationIdTest() {
        Author author = authorService.loadByName("Mark Twain1");
        Genre genre  = genreService.loadByName("романтическая комедия");
        bookService.createBook(new Book(author,genre,"Some book"));
        Assert.assertEquals("Some book", bookService.loadByName("Some book").getBookName());
    }

    @Test
    public void bookFindByNameTest() {
        Assert.assertEquals("Some book 2", bookService.loadByName("Some book 2").getBookName());
    }

    @Test
    public void bookDeleteByNameTest() {
        bookService.deleteByName("Some book 3");
        Optional<Book> book = bookService.findByName("Some book 3");
        Assert.assertFalse(book.isPresent());
    }

    @Test
    public void bookListAllTest() {
        List<Book> books = bookService.listBooks();
        Assert.assertEquals(7, books.size());
    }
}
