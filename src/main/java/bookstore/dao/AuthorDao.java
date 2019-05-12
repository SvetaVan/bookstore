package bookstore.dao;


import bookstore.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {

    Author creteAuthor(Author author);

    Author loadByName(String authorName);

    Optional<Author> findByName(String authorName);

    Author findById(Integer id);

    void deleteByName(String authorName);

    List<Author> listAll();
}
