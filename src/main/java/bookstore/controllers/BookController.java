package bookstore.controllers;

import bookstore.controllers.dto.BookDTO;
import bookstore.entity.Author;
import bookstore.entity.Book;
import bookstore.entity.Genre;
import bookstore.exception.BookStoreException;
import bookstore.services.AuthorService;
import bookstore.services.BookService;
import bookstore.services.GenreService;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    public BookController(BookService bookService, AuthorService authorService, GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @GetMapping()
    public ResponseEntity<List<BookDTO>> view(
            @RequestParam(value = "name", required = false) String name) throws NotFoundException {
        List<BookDTO> bookDTOs = new ArrayList<>();
        if (name != null) {
            Book book = bookService.findByName(name);
            if (book == null) {
                throw new NotFoundException("Book does not exist");
            }
            BookDTO bookDTO = new BookDTO(book.getAuthor().getAuthorName(), book.getGenre().getGenreName(), book.getBookName());
            bookDTOs.add(bookDTO);
        } else {
            List<Book> listBooks = bookService.listBooks();
            for (Book book : listBooks) {
                BookDTO bookDTO = new BookDTO(book.getAuthor().getAuthorName(), book.getGenre().getGenreName(), book.getBookName());
                bookDTOs.add(bookDTO);
            }
        }
        return new ResponseEntity<>(bookDTOs, HttpStatus.OK);
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> createBook(@RequestBody BookDTO bookDTO) {

        Book book = bookService.findByName(bookDTO.getBookName());
        if (book != null) {
            throw new BookStoreException("Book already exists!");
        }
        Author author = authorService.findByName(bookDTO.getAuthor().getAuthorName());
        if (author == null) {
            author = authorService.createAuthor(new Author(bookDTO.getAuthor().getAuthorName()));
        }
        Genre genre = genreService.findByName(bookDTO.getGenre().getGenreName());
        if (genre == null) {
            genre = genreService.createGenre(new Genre(bookDTO.getGenre().getGenreName()));
        }
        Book bookCreated = bookService.createBook(new Book(author, genre, bookDTO.getBookName()));

        return new ResponseEntity<>(bookCreated, HttpStatus.CREATED);
    }


    @DeleteMapping("/delete/{name}")
    public ResponseEntity<Object> delete(@PathVariable("name") String name) {
        bookService.deleteByName(name);
        return new ResponseEntity<>("Object is deleted successfully", HttpStatus.OK);
    }

}
