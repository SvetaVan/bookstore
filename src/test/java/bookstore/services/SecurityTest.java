package bookstore.services;

import bookstore.controllers.BookController;
import bookstore.entity.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import reactor.core.publisher.Flux;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
@AutoConfigureMockMvc()
public class SecurityTest {

    @MockBean
    BookService bookService;

    @MockBean
    AuthorService authorService;

    @MockBean
    GenreService genreService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(roles = "USER")
    public void getAllBooks() throws Exception {
        Mockito
                .when(bookService.listBooks())
                .thenReturn(Flux.just(new Book(), new Book()));
        mockMvc.perform(get("/flux/book/all"))
                .andExpect(status().isOk());
    }
}