package bookstore.intagration.readers;

import bookstore.entity.Book;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

@Service
public class PremiumReader {

    public String handleMessage(Book book) throws MessagingException {
        return "Hi, this is PremiumReader #" + System.identityHashCode(this) +
                ". " + "Received book - " + book.toString() + "\n";
    }

}
