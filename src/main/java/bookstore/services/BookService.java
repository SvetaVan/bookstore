package bookstore.services;

import bookstore.entity.Book;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookService {

    Mono<Book> createBook(Book book);

    Mono<Book> findByName(String bookName);

    Mono<Book> findById(String id);

    Mono<Book> deleteByName(String bookName);

    Flux<Book> listBooks();


}
