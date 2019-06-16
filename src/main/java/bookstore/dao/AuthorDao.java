package bookstore.dao;


import bookstore.entity.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthorDao extends MongoRepository<Author, Integer> {

    Author findByAuthorName(String authorName);

    void deleteByAuthorName(String authorName);

}
