package bookstore.services;

import bookstore.entity.Author;

import java.util.List;

public interface AuthorService {

    Author createAuthor(Author author);

    Author findByName(String authorName);

    Author findById(Integer id);

    void deleteByName(String authorName);

    List<Author> listAll();

}
