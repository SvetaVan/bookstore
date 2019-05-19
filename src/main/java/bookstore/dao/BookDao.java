package bookstore.dao;

import bookstore.entity.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookDao extends CrudRepository<Book, Integer> {

    Book findByBookName(String bookName);

    @Query("delete from Book a where a.bookName = :book_name")
    void deleteByName(@Param("book_name") String bookName);
}
