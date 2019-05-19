package bookstore.services.Impl;

import bookstore.dao.GenreDao;
import bookstore.entity.Genre;
import bookstore.services.GenreService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GenreServiceImpl implements GenreService {

    final private GenreDao genreDao;

    @Autowired
    public GenreServiceImpl(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public Genre createGenre(Genre genre) {
        return genreDao.save(genre);
    }

    @Override
    public Genre findByName(String genreName) {
        return genreDao.findByGenreName(genreName);
    }

    @Override
    public Optional<Genre> findById(Integer id) {
        return genreDao.findById(id);
    }

    @Override
    public void deleteByName(String genreName) {
        genreDao.deleteByName(genreName);
    }

    @Override
    public List<Genre> listAll() {
        return Lists.newArrayList(genreDao.findAll());
    }
}
