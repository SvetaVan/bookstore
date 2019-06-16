package bookstore.dao;

import bookstore.entity.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GenreDao extends MongoRepository<Genre, Integer> {

    Genre findByGenreName(String genreName);

    void deleteByGenreName(String genreName);
}
