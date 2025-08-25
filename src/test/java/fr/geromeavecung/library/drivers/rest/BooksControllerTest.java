package fr.geromeavecung.library.drivers.rest;

import fr.geromeavecung.library.domain.boundedcontexts.books.Book;
import fr.geromeavecung.library.domain.boundedcontexts.books.BookTitle;
import fr.geromeavecung.library.domain.boundedcontexts.books.ISBN;
import fr.geromeavecung.library.domain.boundedcontexts.books.PublicationDate;
import fr.geromeavecung.library.domain.usecases.CreateABook;
import fr.geromeavecung.library.domain.usecases.CreateABookCommand;
import fr.geromeavecung.library.domain.usecases.SearchABookByIdentifier;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = {BooksController.class})
@WebMvcTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BooksControllerTest {

    private static final Book FOUNDATION = new Book(new ISBN("978-0553293357"), new BookTitle("Foundation"), PublicationDate.read(1951), UUID.fromString("c6625e54-d4e8-4ba0-942e-d285839527e1"));

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CreateABook createABook;

    @MockitoBean
    private SearchABookByIdentifier searchABookByIdentifier;

    @Test
    void create_a_book() throws Exception {
        when(createABook.execute(new CreateABookCommand("978-0553293357", "Foundation", "1951", "c6625e54-d4e8-4ba0-942e-d285839527e1"))).thenReturn(FOUNDATION);

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "isbn": "978-0553293357",
                                    "title": "Foundation",
                                    "publicationDate": "1951",
                                    "authorIdentifier": "c6625e54-d4e8-4ba0-942e-d285839527e1"
                                }"""))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "isbn": "978-0553293357",
                            "title": "Foundation",
                            "publicationDate": "1951",
                            "authorIdentifier": "c6625e54-d4e8-4ba0-942e-d285839527e1"
                        }"""));

    }

    @Test
    void search_a_book_by_identifier() throws Exception {
        when(searchABookByIdentifier.execute(new ISBN("978-0553293357"))).thenReturn(FOUNDATION);

        mockMvc.perform(get("/api/books/978-0553293357"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "isbn": "978-0553293357",
                            "title": "Foundation",
                            "publicationDate": "1951",
                            "authorIdentifier": "c6625e54-d4e8-4ba0-942e-d285839527e1"
                        }"""));

    }

}