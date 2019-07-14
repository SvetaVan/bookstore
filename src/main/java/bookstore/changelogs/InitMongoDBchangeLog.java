package bookstore.changelogs;


import bookstore.entity.Author;
import bookstore.entity.Book;
import bookstore.entity.Genre;
import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import org.springframework.data.mongodb.core.MongoTemplate;

@ChangeLog(order = "001")
public class InitMongoDBchangeLog {

    private Author authorId1;
    private Author authorId2;
    private Genre genreId2;
    private Genre genreId3;

    @ChangeSet(order = "000", id = "dropDB", author = "svetlanavanyushina", runAlways = true)
    public void dropDB(MongoDatabase database){
        database.drop();
    }

    @ChangeSet(order = "001", id = "initAuthors", author = "svetlanavanyushina", runAlways = true)
    public void initAuthors(MongoTemplate template){
        Author author1 = new Author("Bram Stoker");
        Author author2 = new Author("Mark Twain");
        authorId1 = template.insert(author1);
        authorId2 = template.insert(author2);
    }

    @ChangeSet(order = "002", id = "initGenres", author = "svetlanavanyushina", runAlways = true)
    public void initGeners(MongoTemplate template){
        Genre genre1 = new Genre("adventure");
        Genre genre2 = new Genre("comedy");
        Genre genre3 = new Genre("thriller");
        genreId2 = template.save(genre2);
        genreId3 = template.save(genre3);
    }

    @ChangeSet(order = "003", id = "initBooks", author = "svetlanavanyushina", runAlways = true)
    public void initBooks(MongoTemplate template){


        Book book1 = new Book(authorId1, genreId2, "Dracula");
        Book book2 = new Book(authorId2, genreId3, "The Million Pound Bank Note");
        template.save(book1);
        template.save(book2);
    }
}
