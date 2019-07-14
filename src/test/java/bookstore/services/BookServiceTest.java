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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DisplayName("BookServiceTest")
public class BookServiceTest extends AbstractRepositoryTest {
    @Autowired
    private BookService bookService;
    @Autowired
    private GenreService genreService;
    @Autowired
    private AuthorService authorService;

    @Test
    public void setIdOnSave() {
        Mono<Author> authorMono = authorService.createAuthor("Bill1");
        Mono<Genre> genreMono = genreService.createGenre("Драма1");
        Book book = new Book();
        authorMono.doOnNext(book::setAuthor);
        genreMono.doOnNext(book::setGenre);
        book.setBookName("Very interesting book");
        Mono<Book> bookMono = bookService.createBook(book);

        StepVerifier
                .create(bookMono)
                .assertNext(bookCreated -> assertNotNull(bookCreated.getId()))
                .expectComplete()
                .verify();
    }

    //не проходит с ошибкой
    //java.lang.AssertionError: expectation "assertNext" failed (expected: onNext(); actual: onComplete())
    @Test
    public void bookDeleteByNameTest() {
        Mono<Author> authorMono = authorService.createAuthor("Bill22");
        Mono<Genre> genreMono = genreService.createGenre("Драма22");
        Book book = new Book();
        authorMono.doOnNext(book::setAuthor);
        genreMono.doOnNext(book::setGenre);
        book.setBookName("Very interesting book2");
        Mono<Book> bookMono = bookService.createBook(book);

        bookService.deleteByName("Very interesting book2");
        Mono<Book> notFoundMono = bookService.findByName("Very interesting book2");

        StepVerifier
                .create(notFoundMono)
                .assertNext(bookDeleted -> Assert.assertNull(bookDeleted.getId()))
                .expectComplete()
                .verify();
    }

    @Test
    public void bookListAllTest() {
        Flux<Book> bookFlux = bookService.listBooks();
        Mono<Long> count = bookFlux.count();

        StepVerifier
                .create(count)
                .assertNext(countBooks -> Assert.assertEquals(Long.valueOf(2), countBooks))
                .expectComplete()
                .verify();
    }
}
