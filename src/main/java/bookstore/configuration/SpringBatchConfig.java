package bookstore.configuration;

import bookstore.batchjob.AuthorItem;
import bookstore.batchjob.BookItem;
import bookstore.batchjob.GenreItem;
import bookstore.entity.Book;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.sql.DataSource;
import java.util.HashMap;

@EnableBatchProcessing
@Configuration
public class SpringBatchConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Bean
    public ItemReader<Book> bookReader() {
        MongoItemReader<Book> reader = new MongoItemReader<Book>();
        reader.setTemplate(mongoTemplate);
        reader.setSort(new HashMap<>());
        reader.setTargetType(Book.class);
        reader.setQuery("{}");
        return reader;
    }

    @Bean
    public ItemProcessor<Book, BookItem> bookProcessor() {
        return item -> new BookItem(
                item.getAuthor().getAuthorId(),
                item.getGenre().getGenreId(),
                item.getBookName()
        );
    }

    @Bean
    public ItemProcessor<Book, AuthorItem> authorProcessor() {
        return item -> new AuthorItem(
                item.getAuthor().getAuthorId(),
                item.getAuthor().getAuthorName()
        );
    }

    @Bean
    public ItemProcessor<Book, GenreItem> genreProcessor() {
        return item -> new GenreItem(
                item.getGenre().getGenreId(),
                item.getGenre().getGenreName()
        );
    }

    @Autowired
    public DataSource dataSource;

    private final String INSERT_BOOK_QUERY = "insert into books (author_id, genre_id, book_name)" +
            " values (:itemAuthorId, :itemGenreId, :itemBookName)";
    private final String INSERT_AUTHOR_QUERY = "insert into authors (author_id, author_name)" +
            " values (:authorId, :authorName)";
    private final String INSERT_GENRE_QUERY = "insert into genres (genre_id, genre_name)" +
            " values (:genreId, :genreName)";


    @Bean
    public JdbcBatchItemWriter<BookItem> bookWriter() {
        JdbcBatchItemWriter<BookItem> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(
                new BeanPropertyItemSqlParameterSourceProvider<>());

        writer.setSql(INSERT_BOOK_QUERY);
        writer.setDataSource(dataSource);
        return writer;
    }

    @Bean
    public JdbcBatchItemWriter<AuthorItem> authorWriter() {
        JdbcBatchItemWriter<AuthorItem> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(
                new BeanPropertyItemSqlParameterSourceProvider<>());

        writer.setSql(INSERT_AUTHOR_QUERY);
        writer.setDataSource(dataSource);
        return writer;
    }

    @Bean
    public JdbcBatchItemWriter<GenreItem> genreWriter() {
        JdbcBatchItemWriter<GenreItem> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(
                new BeanPropertyItemSqlParameterSourceProvider<>());

        writer.setSql(INSERT_GENRE_QUERY);
        writer.setDataSource(dataSource);
        return writer;
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Book, BookItem>chunk(10)
                .reader(bookReader())
                .processor(bookProcessor())
                .writer(bookWriter())
                .build();
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .<Book, AuthorItem>chunk(10)
                .reader(bookReader())
                .processor(authorProcessor())
                .writer(authorWriter())
                .build();
    }

    @Bean
    public Step step3() {
        return stepBuilderFactory.get("step3")
                .<Book, GenreItem>chunk(10)
                .reader(bookReader())
                .processor(genreProcessor())
                .writer(genreWriter())
                .build();
    }


    @Bean
    public Job importUserJob() {
        return jobBuilderFactory.get("importDatabaseJob")
                .start(step1())
                .start(step2())
                .start(step3())
                .build();
    }


}
