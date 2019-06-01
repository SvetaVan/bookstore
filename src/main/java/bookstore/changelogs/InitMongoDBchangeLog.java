package bookstore.changelogs;


import bookstore.entity.Author;
import bookstore.entity.Book;
import bookstore.entity.Genre;
import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import lombok.val;
import org.springframework.data.mongodb.core.MongoTemplate;

@ChangeLog(order = "001")
public class InitMongoDBchangeLog {

    private Author author1;
    private Author author2;
    private Genre genre1;
    private Genre genre2;
    private Genre genre3;

    @ChangeSet(order = "000", id = "dropDB", author = "svetlanavanyushina", runAlways = true)
    public void dropDB(MongoDatabase database){
        database.drop();
    }

    @ChangeSet(order = "001", id = "initAuthors", author = "svetlanavanyushina", runAlways = true)
    public void initAuthors(MongoTemplate template){
        author1 = new Author("Bram Stoker");
        author2 = new Author("Mark Twain");
        template.insert(author1);
        template.insert(author2);
    }

    @ChangeSet(order = "002", id = "initGenres", author = "svetlanavanyushina", runAlways = true)
    public void initGeners(MongoTemplate template){
        genre1 = new Genre("adventure");
        genre2 = new Genre("comedy");
        genre3 = new Genre("thriller");
        template.save(genre1);
        template.save(genre2);
        template.save(genre3);
    }

    @ChangeSet(order = "003", id = "initBooks", author = "svetlanavanyushina", runAlways = true)
    public void initBooks(MongoTemplate template){
        val book1 = new Book(author1, genre3, "Dracula");
        val book2 = new Book(author2, genre1, "The Million Pound Bank Note");
        template.save(book1);
        template.save(book2);
    }

}
