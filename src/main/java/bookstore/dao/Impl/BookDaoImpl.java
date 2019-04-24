package bookstore.dao.Impl;

import bookstore.dao.BookDao;
import bookstore.entity.Book;
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
public class BookDaoImpl implements BookDao {

    private final NamedParameterJdbcTemplate jdbc;

    @Autowired
    public BookDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbc = namedParameterJdbcTemplate;
    }

    private final String INSERT_QUERY = "insert into books (author_id, genre_id, book_name)" +
            " values (:author_id, :genre_id, :book_name)";

    private final String FIND_BY_NAME_QUERY = "select id, author_id, genre_id, book_name from books " +
            " where book_name = :book_name";

    private final String FIND_BY_ID_QUERY = "select id, author_id, genre_id, book_name from books " +
            " where id = :id";

    private final String DELETE_BY_NAME_QUERY = "delete from books" +
            " where book_name = :book_name";

    private final String LIST_ALL_BOOKS = "select * from books";

    @Override
    public Book createBook(Book book) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update(INSERT_QUERY,
                new MapSqlParameterSource("author_id", book.getAuthorId())
                        .addValue("genre_id", book.getGenreId())
                        .addValue("book_name", book.getBookName()),
                keyHolder
        );

        return new Book((Integer) keyHolder.getKey(), book);
    }

    @Override
    public Book findByName(String bookName) {
        try {
            return jdbc.queryForObject(FIND_BY_NAME_QUERY,
                    new MapSqlParameterSource("book_name", bookName),
                    new BookMapper()
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Book findById(int id) {
        try {
            return jdbc.queryForObject(FIND_BY_ID_QUERY,
                    new MapSqlParameterSource("id", id),
                    new BookMapper()
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void deleteByName(String bookName) {
        jdbc.update(DELETE_BY_NAME_QUERY,
                new MapSqlParameterSource("book_name", bookName)
        );
    }

    @Override
    public List<Book> listBooks() {
        return jdbc.query(LIST_ALL_BOOKS,
                new BookMapper()
        );
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int i) throws SQLException {
            int id = rs.getInt("id");
            int author_id = rs.getInt("author_Id");
            int genre_id = rs.getInt("genre_Id");
            String book_name = rs.getString("book_name");
            return new Book(id, author_id, genre_id, book_name);
        }
    }
}
