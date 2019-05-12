package bookstore.services;


import bookstore.entity.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {

    Genre createGenre(Genre genre);

    Genre loadByName(String genreName);

    Optional<Genre> findByName(String genreName);

    Genre findById(Integer id);

    void deleteByName(String genreName);

    List<Genre> listAll();
}
