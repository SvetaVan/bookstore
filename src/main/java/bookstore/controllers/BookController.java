package bookstore.controllers;

import bookstore.entity.Book;
import bookstore.entity.dto.BookDTO;
import bookstore.services.AuthorService;
import bookstore.services.BookService;
import bookstore.services.GenreService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/flux/book")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Autowired
    public BookController(BookService bookService, AuthorService authorService, GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @GetMapping(path = "/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @PreAuthorize("hasRole('USER')")
    public Flux<String> allStream() {
        return bookService.listBooks()
                .map(Book::toString)
                .delayElements(Duration.ofSeconds(1L));
    }

    @GetMapping("/{name}")
    @PreAuthorize("hasRole('USER')")
    public Mono<BookDTO> viewBook(@PathVariable("name") String name) throws NotFoundException {
        return bookService.findByName(name)
                .map(book -> new BookDTO(book.getAuthor().getAuthorName(), book.getGenre().getGenreName(), book.getBookName()));
    }


    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<Book> createBook(@RequestBody BookDTO bookDTO) {
        return Mono.zip(Mono.just(bookDTO),
                authorService.createAuthor(bookDTO.getAuthorName()),
                genreService.createGenre(bookDTO.getGenreName()))
                .flatMap(tuple -> {
                            Book bookToSave = new Book();
                            bookToSave.setBookName(tuple.getT1().getBookName());
                            bookToSave.setAuthor(tuple.getT2());
                            bookToSave.setGenre(tuple.getT3());
                            return bookService.createBook(bookToSave);
                        }
                );
    }

    @DeleteMapping("/delete/{name}")
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<Book> delete(@PathVariable("name") String name) {
        return bookService.findByName(name)
                .flatMap(oldBook ->
                        bookService.deleteByName(name)
                                .then(Mono.just(oldBook))
                )
                .single();
    }

}
