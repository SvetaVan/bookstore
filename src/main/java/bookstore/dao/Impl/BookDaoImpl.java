package bookstore.dao.Impl;

import bookstore.dao.BookDao;
import bookstore.entity.Author;
import bookstore.entity.Book;
import bookstore.exception.BookStoreException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class BookDaoImpl implements BookDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Book createBook(Book book) {
        entityManager.persist(book);
        return new Book(book.getId(), book);
    }

    @Override
    public Book loadByName(String bookName) {
        TypedQuery<Book> query = entityManager.createQuery(
                "select b from Book b where b.bookName = :bookName"
                , Book.class);
        query.setParameter("bookName", bookName);
        return query.getSingleResult();
    }

    @Override
    public Optional<Book> findByName(String bookName) {
        TypedQuery<Book> query = entityManager.createQuery(
                "select b from Book b where b.bookName = :bookName"
                , Book.class);
        query.setParameter("bookName", bookName);
        List<Book> resultList = query.getResultList();
        if (resultList.size() == 0) {
            return Optional.empty();
        } else if (resultList.size() > 1) {
            throw new BookStoreException("Result set is more than 1.");
        } else {
            Book book = resultList.get(0);
            return Optional.of(book);
        }
    }

    @Override
    public Book findById(int id) {
        return entityManager.find(Book.class, id);
    }

    @Override
    public void deleteByName(String bookName) {
        Query query = entityManager.createQuery("delete from Book b where b.bookName = :book_name");
        query.setParameter("book_name", bookName);
        query.executeUpdate();
    }

    @Override
    public List<Book> listBooks() {
        TypedQuery<Book> query = entityManager.createQuery(
                "select b from Book b"
                , Book.class);
        return query.getResultList();
    }
}
