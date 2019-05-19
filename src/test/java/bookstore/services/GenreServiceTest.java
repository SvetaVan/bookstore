package bookstore.services;

import bookstore.entity.Genre;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"})
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
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
        Genre foundGenre = genreService.findByName("боевик");
        Assert.assertEquals("боевик", foundGenre.getGenreName());
    }

    @Test
    public void genreDeleteByNameTest() {
        genreService.deleteByName("романтическая комедия");
        Genre genre = genreService.findByName("романтическая комедия");
        Assert.assertNull(genre);
    }

    @Test
    public void genreListAllTest() {
        List<Genre> genres = genreService.listAll();
        Assert.assertEquals(11, genres.size());
    }

}
