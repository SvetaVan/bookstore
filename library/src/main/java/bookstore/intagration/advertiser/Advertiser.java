package bookstore.intagration.advertiser;

import bookstore.entity.Book;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Advertiser {

    public Collection<String> advertiseBook(List<Book> books) {
        return books
                .stream()
                .map(book->"Dear UsualReader, Just Arrived - " + book.toString())
                .collect(Collectors.toList());
    }

}
