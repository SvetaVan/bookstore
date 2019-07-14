package bookstore.services;

import bookstore.entity.Genre;
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
@DisplayName("GenreServiceTest")
@DataMongoTest
public class GenreServiceTest extends AbstractRepositoryTest{

    @Autowired
    private GenreService genreService;

    @Test
    public void setIdOnSave() {
        Mono<Genre> genreMono = genreService.createGenre("Драма");

        StepVerifier
                .create(genreMono)
                .assertNext(genre -> assertNotNull(genre.getGenreId()))
                .expectComplete()
                .verify();
    }

    //не проходит с ошибкой
    //org.springframework.data.mapping.MappingException: Couldn't find PersistentEntity for type class java.lang.Void!
    //Вроде говорили, что можно Void использовать, но не сложилось((
    @Test
    public void genreDeleteByNameTest() {
        Mono<Genre> genreMono = genreService.createGenre("Драма");
        genreService.deleteByName("Драма");
        Mono<Genre> notFoundMono = genreService.findByName("Драма");

        StepVerifier
                .create(notFoundMono)
                .assertNext(genre -> Assert.assertNull(genre.getGenreId()))
                .expectComplete()
                .verify();
    }

    @Test
    public void genreListAllTest() {
        Flux<Genre> genreFlux = genreService.listAll();
        Mono<Long> count = genreFlux.count();

        StepVerifier
                .create(count)
                .assertNext(countAuthors -> Assert.assertEquals(Long.valueOf(2),countAuthors))
                .expectComplete()
                .verify();
    }

}
