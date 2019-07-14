package bookstore.services;

import bookstore.entity.Author;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@DisplayName("AuthorServiceTest")
@DataMongoTest
public class AuthorServiceTest extends AbstractRepositoryTest{

    @Autowired
    private AuthorService authorService;

    @Test
    public void setIdOnSave() {
        Mono<Author> authorMono = authorService.createAuthor("Bill");

        StepVerifier
                .create(authorMono)
                .assertNext(author -> assertNotNull(author.getAuthorId()))
                .expectComplete()
                .verify();
    }

    //не проходит с ошибкой
    //java.lang.AssertionError: expectation "assertNext" failed (expected: onNext(); actual: onComplete())
    @Test
    public void authorDeleteByNameTest() {
        Mono<Author> authorMono = authorService.createAuthor("Bill");
        authorService.deleteByName("Bill");
        Mono<Author> notFoundMono = authorService.findByName("Bill");

        StepVerifier
                .create(notFoundMono)
                .assertNext(author -> Assert.assertNull(author.getAuthorId()))
                .expectComplete()
                .verify();
    }

    @Test
    public void authorListAllTest() {
        Flux<Author> authorFlux = authorService.listAll();
        Mono<Long> count = authorFlux.count();

        StepVerifier
                .create(count)
                .assertNext(countAuthors -> Assert.assertEquals(Long.valueOf(2),countAuthors))
                .expectComplete()
                .verify();
    }

}
