package bookstore.controllers;

import bookstore.entity.Author;
import bookstore.entity.Book;
import bookstore.entity.Genre;
import bookstore.entity.dto.BookDTO;
import bookstore.entity.dto.BookDTOMapper;
import bookstore.services.AuthorService;
import bookstore.services.BookService;
import bookstore.services.GenreService;
import javassist.NotFoundException;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple3;

import java.time.Duration;

@RestController
@RequestMapping("/flux/book")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final BookDTOMapper bookDTOMapper;

    @Autowired
    public BookController(BookService bookService, AuthorService authorService, GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
        this.bookDTOMapper = Mappers.getMapper(BookDTOMapper.class);
    }

    @GetMapping(path = "/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> allStream() {
        return bookService.listBooks()
                .map(Book::toString)
                .delayElements(Duration.ofSeconds(1L));
    }

    @GetMapping("/{name}")
    public Mono<BookDTO> viewBook(@PathVariable("name") String name) throws NotFoundException {
        return bookService.findByName(name)
                .map(book -> new BookDTO(book.getAuthor().getAuthorName(), book.getGenre().getGenreName(), book.getBookName()));
    }


    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<Book> createBook(@RequestBody BookDTO bookDTO) {
        Book bookToSave = new Book();
        Mono<Tuple3<BookDTO, Author, Genre>> zip = Mono.zip(Mono.just(bookDTO)
                , authorService.createAuthor(bookDTO.getAuthor().getAuthorName())
                , genreService.createGenre(bookDTO.getGenre().getGenreName()));

        zip.doOnNext(tuple -> {
                    bookToSave.setBookName(tuple.getT1().getBookName());
                    bookToSave.setAuthor(tuple.getT2());
                    bookToSave.setGenre(tuple.getT3());
                }
        );
        return bookService.createBook(bookToSave);
    }

    @DeleteMapping("/delete/{name}")
    public Mono<Book> delete(@PathVariable("name") String name) {
        return bookService.findByName(name)
                .flatMap(oldBook ->
                        bookService.deleteByName(name)
                                .then(Mono.just(oldBook))
                )
                .single();
    }

}
