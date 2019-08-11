package bookstore.intagration;


import bookstore.entity.Book;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import java.util.List;

@MessagingGateway
public interface BookStore {
    @Gateway(requestChannel = "advertisementChannel", replyChannel = "replyChannel")
    List<String> process(List<Book> orderItem);
}