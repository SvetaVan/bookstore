package bookstore.dao.Impl;

import bookstore.dao.AuthorDao;
import bookstore.entity.Author;
import bookstore.exception.BookStoreException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorDaoImpl implements AuthorDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Author creteAuthor(Author author) {
        entityManager.persist(author);
        return new Author(author.getId(), author.getAuthorName());
    }

    @Override
    public Author loadByName(String authorName) {
        TypedQuery<Author> query = entityManager.createQuery("select a from Author a where a.authorName = :author_name"
                , Author.class);
        query.setParameter("author_name", authorName);
        return query.getSingleResult();
    }

    @Override
    public Optional<Author> findByName(String authorName){
        Query query = entityManager.createQuery("select a from Author a where a.authorName = :author_name"
                , Author.class);
        query.setParameter("author_name", authorName);
        List<Author> resultList = query.getResultList();
        if (resultList.size() == 0) {
            return Optional.empty();
        } else if (resultList.size() > 1) {
            throw new BookStoreException("Result set is more than 1.");
        } else {
            Author author = resultList.get(0);
            return Optional.of(author);
        }
    }

    @Override
    public Author findById(Integer id) {
        return entityManager.find(Author.class, id);
    }

    @Override
    public void deleteByName(String authorName) {
        Query query = entityManager.createQuery("delete from Author a where a.authorName = :author_name");
        query.setParameter("author_name", authorName);
        query.executeUpdate();
    }

    @Override
    public List<Author> listAll() {
        TypedQuery<Author> query = entityManager.createQuery("select a from Author a"
                , Author.class);
        return query.getResultList();
    }
}
