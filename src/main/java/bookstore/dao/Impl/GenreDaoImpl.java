package bookstore.dao.Impl;

import bookstore.dao.GenreDao;
import bookstore.entity.Author;
import bookstore.entity.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Transactional
public class GenreDaoImpl implements GenreDao {

    private final NamedParameterJdbcTemplate jdbc;

    @Autowired
    public GenreDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbc = namedParameterJdbcTemplate;
    }

    private final String INSERT_QUERY = "insert into genres (genre_name)" +
            " values ( :genre_name)";

    private final String FIND_BY_NAME_QUERY = "select id, genre_name from genres " +
            " where genre_name = :genre_name";

    private final String FIND_BY_ID_QUERY = "select id, genre_name from genres " +
            " where id = :id";

    private final String DELETE_BY_NAME_QUERY = "delete from genres" +
            " where genre_name = :genre_name";

    private final String LIST_ALL_QUERY = "select * from genres";

    @Override
    public Genre createGenre(Genre genre) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update(INSERT_QUERY,
                new MapSqlParameterSource("genre_name", genre.getGenreName()),
                keyHolder
        );

        return new Genre((Integer) keyHolder.getKey(), genre);
    }

    @Override
    public Genre findByName(String genreName) {
        try {
            return jdbc.queryForObject(FIND_BY_NAME_QUERY,
                    new MapSqlParameterSource("genre_name", genreName),
                    (rs, rowNum) -> new Genre(
                            rs.getInt("id"),
                            rs.getString("genre_name"))
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Genre findById(Integer id) {
        try {
            return jdbc.queryForObject(FIND_BY_ID_QUERY,
                    new MapSqlParameterSource("id", id),
                    (rs, rowNum) -> new Genre(
                            rs.getInt("id"),
                            rs.getString("genre_name"))
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void deleteByName(String genreName) {
        jdbc.update(DELETE_BY_NAME_QUERY,
                new MapSqlParameterSource("genre_name", genreName)
        );
    }

    @Override
    public List<Genre> listAll() {
        return jdbc.query(LIST_ALL_QUERY,
                (rs, rowNum) -> new Genre(
                        rs.getInt("id"),
                        rs.getString("genre_name"))
        );
    }

    private static class GenreMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("genre_name");
            return new Genre(id, name);
        }
    }
}
