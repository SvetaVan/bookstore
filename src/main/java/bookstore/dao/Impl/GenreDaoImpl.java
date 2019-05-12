package bookstore.dao.Impl;

import bookstore.dao.GenreDao;
import bookstore.entity.Author;
import bookstore.entity.Genre;
import bookstore.exception.BookStoreException;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class GenreDaoImpl implements GenreDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Genre createGenre(Genre genre) {
        entityManager.persist(genre);
        return new Genre(genre.getId(), genre.getGenreName());
    }

    @Override
    public Genre loadByName(String genreName) {
        TypedQuery<Genre> query = entityManager.createQuery("select g from Genre g where g.genreName = :genre_name"
                , Genre.class);
        query.setParameter("genre_name", genreName);
        return query.getSingleResult();
    }

    @Override
    public Optional<Genre> findByName(String genreName) {
        TypedQuery<Genre> query = entityManager.createQuery("select g from Genre g where g.genreName = :genre_name"
                , Genre.class);
        query.setParameter("genre_name", genreName);
        List<Genre> resultList = query.getResultList();
        if (resultList.size() == 0) {
            return Optional.empty();
        } else if (resultList.size() > 1) {
            throw new BookStoreException("Result set is more than 1.");
        } else {
            Genre genre = resultList.get(0);
            return Optional.of(genre);
        }

    }

    @Override
    public Genre findById(Integer id) {
        return entityManager.find(Genre.class, id);
    }

    @Override
    public void deleteByName(String genreName) {
        Query query = entityManager.createQuery("delete from Genre g where g.genreName = :genre_name");
        query.setParameter("genre_name", genreName);
        query.executeUpdate();
    }

    @Override
    public List<Genre> listAll() {
        TypedQuery<Genre> query = entityManager.createQuery("select g from Genre g"
                , Genre.class);
        return query.getResultList();
    }
}
