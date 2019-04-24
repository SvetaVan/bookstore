package bookstore.services.Impl;

import bookstore.dao.AuthorDao;
import bookstore.entity.Author;
import bookstore.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    final private AuthorDao authorDao;

    @Autowired
    public AuthorServiceImpl(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public Author createAuthor(Author author) {
        return authorDao.creteAuthor(author);
    }

    @Override
    public Author findByName(String authorName) {
        return authorDao.findByName(authorName);
    }

    @Override
    public Author findById(Integer id) {
        return authorDao.findById(id);
    }

    @Override
    public void deleteByName(String authorName) {
        authorDao.deleteByName(authorName);
    }

    @Override
    public List<Author> listAll() {
        return authorDao.listAll();
    }
}
