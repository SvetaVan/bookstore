package bookstore.intagration;


import bookstore.entity.Book;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import reactor.core.publisher.Flux;

@MessagingGateway
public interface BookStore {
    @Gateway(requestChannel = "advertisementChannel", replyChannel = "replyChannel")
    void process(Flux<Book> orderItem);
}