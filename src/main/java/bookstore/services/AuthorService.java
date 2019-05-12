package bookstore.services;

import bookstore.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    Author createAuthor(Author author);

    Author loadByName(String authorName);

    Optional<Author> findByName(String authorName);

    Author findById(Integer id);

    void deleteByName(String authorName);

    List<Author> listAll();

}
