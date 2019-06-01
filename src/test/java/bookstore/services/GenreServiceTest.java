package bookstore.services;

import bookstore.entity.Genre;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DisplayName("GenreServiceTest")
public class GenreServiceTest extends AbstractRepositoryTest{

    @Autowired
    private GenreService genreService;

    @Test
    public void genreCreationIdTest() {
        Genre genreCreated = genreService.createGenre(new Genre("драма"));
        Assert.assertEquals("драма", genreCreated.getGenreName());
    }

    @Test
    public void genreFindByNameTest() {
        Genre foundGenre = genreService.findByName("adventure");
        Assert.assertEquals("adventure", foundGenre.getGenreName());
    }

    @Test
    public void genreDeleteByNameTest() {
        genreService.deleteByName("adventure1");
        Genre genre = genreService.findByName("adventure1");
        Assert.assertNull(genre);
    }

    @Test
    public void genreListAllTest() {
        List<Genre> genres = genreService.listAll();
        Assert.assertEquals(3, genres.size());
    }

}
