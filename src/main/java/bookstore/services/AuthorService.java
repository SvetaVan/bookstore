package bookstore.services;

import bookstore.entity.Author;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AuthorService {

    Mono<Author> createAuthor(String authorName);

    Mono<Author> findByName(String authorName);

    Mono<Author> findById(String id);

    Mono<Author> deleteByName(String authorName);

    Flux<Author> listAll();

}
