package bookstore.shell;

import bookstore.entity.Genre;
import bookstore.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;

@ShellComponent
public class GenreCommands {

    private final GenreService genreService;

    @Autowired
    public GenreCommands(GenreService genreService) {
        this.genreService = genreService;
    }

    @ShellMethod("Create genre")
    public String createGenre(@ShellOption String genreName) {
        Genre genre = new Genre(genreName);
        return genreService.createGenre(genre).toString();
    }

    @ShellMethod("Find genre by name")
    public String findGenreByName(@ShellOption String genreName) {
        return genreService.findByName(genreName).toString();
    }

    @ShellMethod("Find genre by id")
    public String findGenreById(@ShellOption Integer id) {
        return genreService.findById(id).toString();
    }

    @ShellMethod("Delete genre by name")
    public String deleteGenre(@ShellOption String genreName) {
        genreService.deleteByName(genreName);
        return String.format("Genre %s is successfully deleted.", genreName);
    }

    @ShellMethod("List all genres")
    public void listAllGenres() {
        List<Genre> genres = genreService.listAll();
        genres.forEach(e -> System.out.println(e.toString()));
    }

}
