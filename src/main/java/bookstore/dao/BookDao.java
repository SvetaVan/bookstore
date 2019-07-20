package bookstore.dao;

import bookstore.entity.Book;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface BookDao extends ReactiveMongoRepository<Book, String> {

    Mono<Book> findByBookName(String bookName);

    Mono<Book> deleteByBookName(String bookName);
}
