package bookstore.services.Impl;

import bookstore.dao.AuthorDao;
import bookstore.entity.Author;
import bookstore.services.AuthorService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

    final private AuthorDao authorDao;

    @Autowired
    public AuthorServiceImpl(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public Author createAuthor(Author author) {
        return authorDao.save(author);
    }

    @Override
    public Author findByName(String authorName) {
        return authorDao.findByAuthorName(authorName);
    }

    @Override
    public Optional<Author> findById(Integer id) {
        return authorDao.findById(id);
    }

    @Override
    public void deleteByName(String authorName) {
        authorDao.deleteByAuthorName(authorName);
    }

    @Override
    public List<Author> listAll() {
        return Lists.newArrayList(authorDao.findAll());
    }
}
