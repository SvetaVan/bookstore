package bookstore.services;


import bookstore.entity.Genre;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GenreService {

    Mono<Genre> createGenre(String genreName);

    Mono<Genre> findByName(String genreName);

    Mono<Genre> findById(String id);

    Mono<Long> deleteByName(String genreName);

    Flux<Genre> listAll();
}
