package bookstore.dao;

import bookstore.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {

    Book createBook(Book book);

    Book loadByName(String bookName);

    Optional<Book> findByName(String bookName);

    Book findById(int id);

    void deleteByName(String bookName);

    List<Book> listBooks();

}
