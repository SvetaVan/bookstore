package bookstore.services;

import bookstore.dao.BookDao;
import bookstore.dao.Impl.BookDaoImpl;
import bookstore.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
    public Book findByName(String bookName) {
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
}
