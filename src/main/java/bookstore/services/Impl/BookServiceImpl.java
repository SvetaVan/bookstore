package bookstore.services.Impl;

import bookstore.dao.BookDao;
import bookstore.entity.Book;
import bookstore.services.BookService;
import com.google.common.collect.Lists;
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
    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public Book createBook(Book book) {
        return bookDao.save(book);
    }

    @Override
    public Book findByName(String bookName) {
        return bookDao.findByBookName(bookName);
    }

    @Override
    public Optional<Book> findById(int id) {
        return bookDao.findById(id);
    }

    @Override
    public void deleteByName(String bookName) {
        bookDao.deleteByName(bookName);
    }

    @Override
    public List<Book> listBooks() {
        return Lists.newArrayList(bookDao.findAll());
    }

    @Override
    //должен сохранить коммент при закрытии транзакции, так как сущность менеджерится при ее извлечении из бд
    public void addComment(int bookId, String comment) {
        Optional<Book> book = bookDao.findById(bookId);
        if(book.isPresent()){
            List<String> comments = book.get().getComments();
            comments.add(comment);
        }
    }

    @Override
    public List<String> listCommentByBook(int bookId) {
        Optional<Book> book = bookDao.findById(bookId);
        return book.get().getComments();
    }
}
