package bookstore.controllers;

import bookstore.entity.Author;
import bookstore.entity.Book;
import bookstore.entity.Genre;
import bookstore.exception.BookStoreException;
import bookstore.services.AuthorService;
import bookstore.services.BookService;
import bookstore.services.GenreService;
import javassist.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller()
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    public BookController(BookService bookService, AuthorService authorService, GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @GetMapping("/book")
    public String viewBook(
            @RequestParam(value = "name",required = false) String name,
            Model model) throws NotFoundException {
        //если параметра не передано, то вызываем форму для ввода данных новой книги
        if(name == null){
            return "createBook";
        }
        Book book = bookService.findByName(name);
        if (book == null) {
            throw new NotFoundException("Book does not exist");
        }
        model.addAttribute("book", book);
        return "book";
    }

    @PostMapping("/createbook")
    public String createBook(@RequestParam(value = "book_name") String bookName
            , @RequestParam(value = "author_name") String authorName
            , @RequestParam(value = "genre_name") String genreName) {

        Book book = bookService.findByName(bookName);
        if (book != null) {
            throw new BookStoreException("Book already exists!");
        }
        Author author = authorService.findByName(authorName);
        if(author == null){
            author = authorService.createAuthor(new Author(authorName));
        }
        Genre genre = genreService.findByName(genreName);
        if(genre == null){
            genre = genreService.createGenre(new Genre(genreName));
        }
        Book bookCreated = bookService.createBook(new Book(author, genre, bookName));

        return "redirect:/";
    }

    @GetMapping("/")
    public String listBooks(Model model) {
        List<Book> books = bookService.listBooks();
        model.addAttribute("books", books);
        return "listall";
    }

    @DeleteMapping("/delete/{name}")
    public String delete(@PathVariable("name") String name) {
        bookService.deleteByName(name);
        return "redirect:/";
    }

}
