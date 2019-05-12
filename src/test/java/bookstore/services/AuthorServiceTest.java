package bookstore.services;

import bookstore.entity.Author;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;


@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"})
@EnableJpaRepositories
@RunWith(SpringRunner.class)
public class AuthorServiceTest {

    @Autowired
    private AuthorService authorService;

   @Test
    public void authorCreationIdTest() {
        Author authorCreated = authorService.createAuthor(new Author("ERNEST HEMINGWAY"));
        Assert.assertEquals("ERNEST HEMINGWAY", authorCreated.getAuthorName());
    }

    @Test
    public void authorFindByNameTest() {
        Author foundAuthor = authorService.loadByName("Oscar Wilde1");
        Assert.assertEquals("Oscar Wilde1", foundAuthor.getAuthorName());
    }

    @Test
    public void authorDeleteByNameTest() {
        authorService.deleteByName("Mark Twain1");
        Optional<Author> name = authorService.findByName("Mark Twain1");
        Assert.assertFalse(name.isPresent());
    }

    @Test
    public void authorListAllTest() {
        List<Author> authors = authorService.listAll();
        Assert.assertEquals(7, authors.size());
    }

}
