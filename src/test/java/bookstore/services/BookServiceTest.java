package bookstore.services;

import bookstore.entity.Book;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"})
@RunWith(SpringRunner.class)
public class BookServiceTest {
    @Autowired
    private BookService bookService;

    @Test
    public void BookCreationIdTest() {
        bookService.createBook(new Book(1,1,"Some book"));
        Assert.assertEquals("Some book", bookService.findByName("Some book").getBookName());
    }

    @Test
    public void bookFindByNameTest() {
        bookService.createBook(new Book(2,2, "Some book 2"));
        Assert.assertEquals("Some book 2", bookService.findByName("Some book 2").getBookName());
    }

    @Test
    public void bookDeleteByNameTest() {
        bookService.createBook(new Book(3,3,"Some book 3"));
        bookService.deleteByName("Some book 3");
        Assert.assertNull(bookService.findByName("Some book 3"));
    }

    @Test
    public void bookListAllTest() {
        List<Book> books = bookService.listBooks();
        Assert.assertEquals(7, books.size());
    }
}
