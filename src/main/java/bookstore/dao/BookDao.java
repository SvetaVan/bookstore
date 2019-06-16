package bookstore.dao;

import bookstore.entity.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookDao extends MongoRepository<Book, Integer> {

    Book findByBookName(String bookName);

    void deleteByBookName(String bookName);
}
