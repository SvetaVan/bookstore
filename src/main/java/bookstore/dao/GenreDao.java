package bookstore.dao;

import bookstore.entity.Genre;

import java.util.List;

public interface GenreDao {

    Genre createGenre(Genre genre);

    Genre findByName(String genreName);

    Genre findById(Integer id);

    void deleteByName(String genreName);

    List<Genre> listAll();
}
