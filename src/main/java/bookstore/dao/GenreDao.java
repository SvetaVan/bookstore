package bookstore.dao;

import bookstore.entity.Genre;
import com.sun.tools.javah.Gen;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GenreDao extends MongoRepository<Genre, Integer> {

    Genre findByGenreName(String genreName);

    void deleteByGenreName(String genreName);
}
