package bookstore.services.Impl;

import bookstore.dao.AuthorDao;
import bookstore.entity.Author;
import bookstore.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

    final private AuthorDao authorDao;

    @Autowired
    public AuthorServiceImpl(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public Mono<Author> createAuthor(String authorName) {
        return authorDao.save(new Author(authorName));
    }

    @Override
    public Mono<Author> findByName(String authorName) {
        return authorDao.findByAuthorName(authorName);
    }

    @Override
    public Mono<Author> findById(String id) {
        return authorDao.findById(id);
    }

    @Override
    public Mono<Author> deleteByName(String authorName) {
        return authorDao.deleteByAuthorName(authorName);
    }

    @Override
    public Flux<Author> listAll() {
        return authorDao.findAll();
    }
}
