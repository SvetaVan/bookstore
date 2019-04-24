package bookstore.services;

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

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"})
@RunWith(SpringRunner.class)
public class GenreServiceTest {
    @Autowired
    private GenreService genreService;

    @Test
    public void genreCreationIdTest() {
        Genre genreCreated = genreService.createGenre(new Genre("драма"));
        Assert.assertEquals("драма", genreCreated.getGenreName());
    }

    @Test
    public void genreFindByNameTest() {
        genreService.createGenre(new Genre("боевик"));
        Genre foundGenre = genreService.findByName("боевик");
        Assert.assertEquals("боевик", foundGenre.getGenreName());
    }

    @Test
    public void genreDeleteByNameTest() {
        genreService.createGenre(new Genre("романтическая комедия"));
        genreService.deleteByName("романтическая комедия");
        Assert.assertNull(genreService.findByName("романтическая комедия"));
    }

    @Test
    public void GenreListAllTest() {
        List<Genre> Genres = genreService.listAll();
        Assert.assertEquals(9, Genres.size());
    }

}
