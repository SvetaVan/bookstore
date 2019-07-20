package bookstore.services.Impl;

import bookstore.dao.GenreDao;
import bookstore.entity.Genre;
import bookstore.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class GenreServiceImpl implements GenreService {

    final private GenreDao genreDao;

    @Autowired
    public GenreServiceImpl(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public Mono<Genre> createGenre(String genreName) {
        return genreDao.save(new Genre(genreName));
    }

    @Override
    public Mono<Genre> findByName(String genreName) {
        return genreDao.findByGenreName(genreName);
    }

    @Override
    public Mono<Genre> findById(String id) {
        return genreDao.findById(id);
    }

    @Override
    public Mono<Long> deleteByName(String genreName) {
        return genreDao.deleteByGenreName(genreName);
    }

    @Override
    public Flux<Genre> listAll() {
        return genreDao.findAll();
    }
}
