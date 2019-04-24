package bookstore.services.Impl;

import bookstore.dao.GenreDao;
import bookstore.entity.Genre;
import bookstore.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    final private GenreDao genreDao;

    @Autowired
    public GenreServiceImpl(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public Genre createGenre(Genre genre) {
        return genreDao.createGenre(genre);
    }

    @Override
    public Genre findByName(String genreName) {
        return genreDao.findByName(genreName);
    }

    @Override
    public Genre findById(Integer id) {
        return genreDao.findById(id);
    }

    @Override
    public void deleteByName(String genreName) {
        genreDao.deleteByName(genreName);
    }

    @Override
    public List<Genre> listAll() {
        return genreDao.listAll();
    }
}
