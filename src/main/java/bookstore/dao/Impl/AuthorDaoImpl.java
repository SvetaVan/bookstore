package bookstore.dao.Impl;

import bookstore.dao.AuthorDao;
import bookstore.entity.Author;
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
public class AuthorDaoImpl implements AuthorDao {

    private final NamedParameterJdbcTemplate jdbc;

    @Autowired
    public AuthorDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbc = namedParameterJdbcTemplate;
    }

    private final String INSERT_QUERY = "insert into authors (author_name)" +
            " values ( :author_name)";

    private final String FIND_BY_NAME_QUERY = "select id, author_name from authors " +
            " where author_name = :author_name";

    private final String FIND_BY_ID_QUERY = "select id, author_name from authors " +
            " where id = :id";

    private final String DELETE_BY_NAME_QUERY = "delete from authors" +
            " where author_name = :author_name";

    private final String LIST_ALL_QUERY = "select * from authors";

    @Override
    public Author creteAuthor(Author author) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update(INSERT_QUERY,
                new MapSqlParameterSource("author_name", author.getAuthorName()),
                keyHolder
        );

        return new Author((Integer) keyHolder.getKey(), author);
    }

    @Override
    public Author findByName(String authorName) {
        try {
            return jdbc.queryForObject(FIND_BY_NAME_QUERY,
                    new MapSqlParameterSource("author_name", authorName),
                    new AuthorMapper()
            );
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public Author findById(Integer id) {
        try {
            return jdbc.queryForObject(FIND_BY_ID_QUERY,
                    new MapSqlParameterSource("id", id),
                    new AuthorMapper()
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void deleteByName(String authorName) {
        jdbc.update(DELETE_BY_NAME_QUERY,
                new MapSqlParameterSource("author_name", authorName)
        );
    }

    @Override
    public List<Author> listAll() {
        return jdbc.query(LIST_ALL_QUERY,
                new AuthorMapper()
        );
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("author_name");
            return new Author(id, name);
        }
    }
}
