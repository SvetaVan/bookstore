package bookstore.services;

import bookstore.entity.Author;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
//@JdbcTest
@SpringBootTest
//@Transactional(propagation = Propagation.NOT_SUPPORTED)
//@ImportAutoConfiguration(Main.class)
public class ServicesTest {

    @Autowired
    private AuthorService authorService;

    @Test
    public void authorCreationTest(){
        Author author = new Author("ERNEST HEMINGWAY");
        Author authorCreated = authorService.createAuthor(author);
        Assert.assertEquals(1, authorCreated.getId());
    }








}
