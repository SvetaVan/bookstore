package bookstore.services;

import bookstore.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Book createBook(Book book);

    Book findByName(String bookName);

    Optional<Book> findById(int id);

    void deleteByName(String bookName);

    List<Book> listBooks();

    void addComment(int bookId, String comment);

    List<String> listCommentByBook (int bookId);

}
