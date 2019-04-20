package bookstore.services;


import bookstore.entity.Genre;

import java.util.List;

public interface GenreService {

    Genre createGenre(Genre genre);

    Genre findByName(String genreName);

    Genre findById(Integer id);

    void deleteByName(String genreName);

    List<Genre> listAll();
}
