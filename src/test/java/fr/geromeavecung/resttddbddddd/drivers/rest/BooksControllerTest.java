package fr.geromeavecung.resttddbddddd.drivers.rest;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.books.Book;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.books.BookTitle;
import fr.geromeavecung.resttddbddddd.domain.usecases.CreateABook;
import fr.geromeavecung.resttddbddddd.domain.usecases.CreateABookCommand;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = {BooksController.class})
@WebMvcTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BooksControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CreateABook createABook;

    @Test
    void create_a_book() throws Exception {
        Book book = new Book(UUID.fromString("1160aed8-eb2f-4fb3-92e4-43480fff64f5"), new BookTitle("Foundation"), UUID.fromString("c6625e54-d4e8-4ba0-942e-d285839527e1"));
        when(createABook.execute(new CreateABookCommand("Foundation", "c6625e54-d4e8-4ba0-942e-d285839527e1"))).thenReturn(book);

        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "title": "Foundation",
                                    "authorIdentifier": "c6625e54-d4e8-4ba0-942e-d285839527e1"
                                }"""))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "bookIdentifier": "1160aed8-eb2f-4fb3-92e4-43480fff64f5",
                            "title": "Foundation",
                            "authorIdentifier": "c6625e54-d4e8-4ba0-942e-d285839527e1"
                        }"""));

    }

}