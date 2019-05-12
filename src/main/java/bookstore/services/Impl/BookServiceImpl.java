package bookstore.services.Impl;

import bookstore.dao.BookDao;
import bookstore.dao.Impl.BookDaoImpl;
import bookstore.entity.Book;
import bookstore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    @Autowired
    public BookServiceImpl(BookDaoImpl bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public Book createBook(Book book) {
        return bookDao.createBook(book);
    }

    @Override
    public Book loadByName(String bookName) {
        return bookDao.loadByName(bookName);
    }

    @Override
    public Optional<Book> findByName(String bookName) {
        return bookDao.findByName(bookName);
    }

    @Override
    public Book findById(int id) {
        return bookDao.findById(id);
    }

    @Override
    public void deleteByName(String bookName) {
        bookDao.deleteByName(bookName);
    }

    @Override
    public List<Book> listBooks() {
        return bookDao.listBooks();
    }

    @Override
    //должен сохранить коммент при закрытии транзакции, так как сущность менеджерится при ее извлечении из бд
    public void addComment(int bookId, String comment) {
        Book book = bookDao.findById(bookId);
        List<String> comments = book.getComments();
        comments.add(comment);
    }

    @Override
    public List<String> listCommentByBook(int bookId) {
        Book book = bookDao.findById(bookId);
        return book.getComments();
    }
}
