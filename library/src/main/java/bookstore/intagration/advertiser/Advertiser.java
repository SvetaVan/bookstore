package bookstore.intagration.advertiser;

import bookstore.entity.Book;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class Advertiser {
    private DirectChannel channel;

   @Value("#{advertisementChannel}")
    public void setChannel(DirectChannel channel) {
        this.channel = channel;
    }

    public void advertiseBook(Book book) {
        System.out.println("Dear Premium Reader, Just Arrived - " + book.toString());
        channel.send(MessageBuilder.withPayload(book).build());
    }

    public DirectChannel getChannel() {
        return channel;
    }
}
