package bookstore.shell;

import bookstore.entity.Author;
import bookstore.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;

@ShellComponent
public class AuthorCommands {

    private final AuthorService authorService;

    @Autowired
    public AuthorCommands(AuthorService authorService) {
        this.authorService = authorService;
    }

    @ShellMethod("Create author")
    public String createAuthor(@ShellOption String authorName) {
        Author author = new Author(authorName);
        return authorService.createAuthor(author).toString();
    }

    @ShellMethod("Find author by name")
    public String findAuthorByName(@ShellOption String authorName) {
        return authorService.findByName(authorName).toString();
    }

    @ShellMethod("Find author by id")
    public String findAuthorById(@ShellOption Integer id) {
        return authorService.findById(id).toString();
    }

    @ShellMethod("Delete author by name")
    public String deleteAuthor(@ShellOption String authorName) {
        authorService.deleteByName(authorName);
        return String.format("Author %s is successfully deleted.", authorName);
    }

    @ShellMethod("List all authors")
    public void listAllAuthors() {
        List<Author> authors = authorService.listAll();
        authors.forEach(e -> System.out.println(e.toString()));
    }
}
