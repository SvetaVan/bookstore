package bookstore.intagration.readers;

import bookstore.entity.Book;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

@Service
public class UsualReader {

    public String handleMessage(Book book) throws MessagingException {
        return "Hi, this is UsualReader #" + System.identityHashCode(this) +
                ". " + "Received book - " + book.toString() + "\n";
    }

}

