package bookstore.entity.dto;

import bookstore.entity.Book;
import org.mapstruct.Mapper;

@Mapper
public interface BookDTOMapper {

    BookDTO bookToDTO(Book book);

    Book dtoToBook(BookDTO bookDTO);


}
