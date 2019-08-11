package bookstore;

import bookstore.entity.Author;
import bookstore.entity.Book;
import bookstore.entity.Genre;
import bookstore.intagration.BookStore;
import bookstore.services.BookService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@EnableConfigurationProperties
@SpringBootApplication
@EnableIntegration
@IntegrationComponentScan
public class Main {
    @Bean
    public QueueChannel advertisementChannel() {
        return MessageChannels.queue(10).get();
    }

    @Bean
    PublishSubscribeChannel usualChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean
    PublishSubscribeChannel premiumChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean
    PublishSubscribeChannel replyChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate(100).maxMessagesPerPoll(2).get();
    }

    @Bean
    public IntegrationFlow bookFlow() {
        return IntegrationFlows.from("advertisementChannel")
                .split()
                .<Book, Boolean>route(
                        Book::isPremium,
                        mapping -> mapping
                                .subFlowMapping(true, sf -> sf
                                        .channel("premiumChannel")
                                        .handle("premiumReader", "handleMessage")
                                )
                                .subFlowMapping(false, sf -> sf
                                        .channel("usualChannel")
                                        .handle("usualReader", "handleMessage")
                                )
                )
                .get();
    }


    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext ctx = SpringApplication.run(Main.class, args);

        BookStore bookStore = ctx.getBean(BookStore.class);
        BookService bookService = ctx.getBean(BookService.class);

        while (true) {
            Thread.sleep(1000);
            final List<Book> books = generateNewBooks();
            Collection<String> bookAdvertisement = bookStore.process(books);
            System.out.println("Advertised books: " + String.join(";", bookAdvertisement));
        }
    }

    private static List<Book> generateNewBooks() {
        int bookCnt = (int) (Math.random() * 5);
        final List<Book> books = new ArrayList<>();
        for (int i = 0; i < bookCnt; i++) {
            final Book book = new Book(
                    new Author("Author" + i),
                    new Genre("Genre" + i),
                    "NewBook" + i);
            final boolean isPremium = i % 2 == 0 ? Boolean.TRUE : Boolean.FALSE;
            book.setPremium(isPremium);
            books.add(book);
        }
        return books;
    }
}

