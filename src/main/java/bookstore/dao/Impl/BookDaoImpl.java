package bookstore.dao.Impl;

import bookstore.dao.BookDao;
import bookstore.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
        return jdbc.queryForObject(FIND_BY_NAME_QUERY,
                new MapSqlParameterSource("book_name", bookName),
                (rs, rowNum) -> new Book(
                        rs.getInt("id"),
                        rs.getInt("author_id"),
                        rs.getInt("genre_id"),
                        rs.getString("book_name"))
                );
    }

    @Override
    public Book findById(int id) {
        return jdbc.queryForObject(FIND_BY_ID_QUERY,
                new MapSqlParameterSource("id", id),
                (rs, rowNum) -> new Book(
                        rs.getInt("id"),
                        rs.getInt("author_Id"),
                        rs.getInt("genre_Id"),
                        rs.getString("book_name"))
        );

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
                (rs, rowNum) -> new Book(
                        rs.getInt("id"),
                        rs.getInt("author_Id"),
                        rs.getInt("genre_Id"),
                        rs.getString("book_name"))
                );
    }
}
