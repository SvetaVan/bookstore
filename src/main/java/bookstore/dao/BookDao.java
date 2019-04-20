package bookstore.dao;

import bookstore.entity.Book;

import java.util.List;

public interface BookDao {

    Book createBook(Book book);

    Book findByName(String bookName);

    Book findById(int id);

    void deleteByName(String bookName);

    List<Book> listBooks();

}
