package bookstore.intagration.readers;

import bookstore.entity.Book;
import org.springframework.integration.MessageRejectedException;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

@Component
public class Reader implements MessageHandler {


    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        Object payload = message.getPayload();

        if (payload instanceof Book) {
            receiveAndAcknowledge((Book) payload);
        } else {
            throw new MessageRejectedException(message, "Unknown data type has been received.");
        }
    }

    void receiveAndAcknowledge(Book book) {
        System.out.println("Hi, this is Reader #" + System.identityHashCode(this) +
                ". " + "Received book - " + book.toString() + "\n");
    }
}

