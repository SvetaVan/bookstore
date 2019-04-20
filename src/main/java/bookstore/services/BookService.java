package bookstore.services;

import bookstore.entity.Book;

import java.util.List;

public interface BookService {

    Book createBook(Book book);

    Book findByName(String bookName);

    Book findById(int id);

    void deleteByName(String bookName);

    List<Book> listBooks();

}
