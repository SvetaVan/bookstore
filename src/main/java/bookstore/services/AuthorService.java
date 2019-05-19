package bookstore.services;

import bookstore.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    Author createAuthor(Author author);

    Author findByName(String authorName);

    Optional<Author> findById(Integer id);

    void deleteByName(String authorName);

    List<Author> listAll();

}
