package bookstore.services;

import bookstore.entity.Author;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;



@RunWith(SpringRunner.class)
@DisplayName("AuthorServiceTest")
public class AuthorServiceTest extends AbstractRepositoryTest{

    @Autowired
    private AuthorService authorService;

    @Test
    public void authorCreationIdTest() {
        Author authorCreated = authorService.createAuthor(new Author("ERNEST HEMINGWAY"));
        Assert.assertEquals("ERNEST HEMINGWAY", authorCreated.getAuthorName());
    }

    @Test
    public void authorDeleteByNameTest() {
        authorService.deleteByName("Bram Stoker1");
        Author name = authorService.findByName("Bram Stoker1");
        Assert.assertNull(name);
    }

    @Test
    public void authorListAllTest() {
        List<Author> authors = authorService.listAll();
        Assert.assertEquals(2, authors.size());
    }

}
