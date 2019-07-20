package bookstore.dao;

import bookstore.entity.Genre;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface GenreDao extends ReactiveMongoRepository<Genre, String> {

    Mono<Genre> findByGenreName(String genreName);

    Mono<Long> deleteByGenreName(String genreName);
}
