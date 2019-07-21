package bookstore.services.Impl;

import bookstore.dao.BookDao;
import bookstore.entity.Book;
import bookstore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    @Autowired
    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public Mono<Book> createBook(Book book) {
        return bookDao.save(book);
    }

    @Override
    public Mono<Book> findByName(String bookName) {
        return bookDao.findByBookName(bookName);
    }

    @Override
    public Mono<Book> findById(String id) {
        return bookDao.findById(id);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<Book> deleteByName(String bookName) {
        return bookDao.deleteByBookName(bookName);
    }

    @Override
    public Flux<Book> listBooks() {
        return bookDao.findAll();
    }
}
