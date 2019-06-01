package bookstore.services;

import bookstore.entity.Author;
import bookstore.entity.Book;
import bookstore.entity.Genre;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DisplayName("BookServiceTest")
public class BookServiceTest extends AbstractRepositoryTest{
    @Autowired
    private BookService bookService;
    @Autowired
    private GenreService genreService;
    @Autowired
    private AuthorService authorService;


    @Test
    public void BookCreationIdTest() {
        Author author = authorService.findByName("Mark Twain1");
        Genre genre  = genreService.findByName("романтическая комедия");
        bookService.createBook(new Book(author,genre,"Some book"));
        Assert.assertEquals("Some book", bookService.findByName("Some book").getBookName());
    }

    @Test
    public void bookFindByNameTest() {
        Assert.assertEquals("Dracula", bookService.findByName("Dracula").getBookName());
    }

    @Test
    public void bookDeleteByNameTest() {
        bookService.deleteByName("Some book 3");
        Book book = bookService.findByName("Some book 3");
        Assert.assertNull(book);
    }

    @Test
    public void bookListAllTest() {
        List<Book> books = bookService.listBooks();
        Assert.assertEquals(2, books.size());
    }
}
