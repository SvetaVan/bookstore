package bookstore.dao;

import bookstore.entity.Author;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface AuthorDao extends ReactiveMongoRepository<Author, String> {

    Mono<Author> findByAuthorName(String authorName);

    Mono<Author> deleteByAuthorName(String authorName);

}
