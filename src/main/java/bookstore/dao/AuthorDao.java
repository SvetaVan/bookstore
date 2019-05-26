package bookstore.dao;


import bookstore.entity.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AuthorDao extends CrudRepository<Author, Integer> {

    Author findByAuthorName(String authorName);

    @Query("delete from Author a where a.authorName = :author_name")
    void deleteByName(@Param("author_name") String authorName);

}
