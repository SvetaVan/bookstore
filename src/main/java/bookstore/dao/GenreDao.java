package bookstore.dao;

import bookstore.entity.Genre;
import com.sun.tools.javah.Gen;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GenreDao extends CrudRepository<Genre, Integer> {

    Genre findByGenreName(String genreName);

    @Query("delete from Genre a where a.genreName = :genre_name")
    void deleteByName(@Param("genre_name") String genreName);
}
