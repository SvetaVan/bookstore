package bookstore.dao;


import bookstore.entity.Author;

import java.util.List;

public interface AuthorDao {

    Author creteAuthor(Author author);

    Author findByName(String authorName);

    Author findById(Integer id);

    void deleteByName(String authorName);

    List<Author> listAll();
}
