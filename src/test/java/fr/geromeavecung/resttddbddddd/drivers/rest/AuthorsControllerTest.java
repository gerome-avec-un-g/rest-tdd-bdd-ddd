package fr.geromeavecung.resttddbddddd.drivers.rest;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.authors.Author;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.books.Book;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.books.BookTitle;
import fr.geromeavecung.resttddbddddd.domain.usecases.CreateAnAuthorCommand;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.authors.SearchForAuthorsCommand;
import fr.geromeavecung.resttddbddddd.domain.usecases.CreateAnAuthor;
import fr.geromeavecung.resttddbddddd.domain.usecases.SearchBooksByAuthor;
import fr.geromeavecung.resttddbddddd.domain.usecases.SearchForAuthors;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Year;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = {AuthorsController.class})
@WebMvcTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AuthorsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CreateAnAuthor createAnAuthor;

    @MockitoBean
    private SearchForAuthors searchForAuthors;

    @MockitoBean
    private SearchBooksByAuthor searchBooksByAuthor;

    @Test
    void create_an_author() throws Exception {
        Author author = new Author(UUID.fromString("1160aed8-eb2f-4fb3-92e4-43480fff64f5"), "Isaac", "Asimov");
        when(createAnAuthor.execute(new CreateAnAuthorCommand("Isaac", "Asimov"))).thenReturn(author);

        mockMvc.perform(post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "firstName": "Isaac",
                                    "lastName": "Asimov"
                                }"""))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "identifier": "1160aed8-eb2f-4fb3-92e4-43480fff64f5",
                            "firstName": "Isaac",
                            "lastName": "Asimov"
                        }"""));

    }

    @Test
    void find_an_author() throws Exception {
        Author author = new Author(UUID.fromString("1160aed8-eb2f-4fb3-92e4-43480fff64f5"), "Isaac", "Asimov");
        when(searchForAuthors.execute(new SearchForAuthorsCommand("Asimov"))).thenReturn(List.of(author));

        mockMvc.perform(get("/authors?searchTerm=Asimov"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                          "authors": [
                            {
                              "identifier": "1160aed8-eb2f-4fb3-92e4-43480fff64f5",
                              "firstName": "Isaac",
                              "lastName": "Asimov"
                            }
                          ]
                        }"""));

    }

    @Test
    void find_books_by_author() throws Exception {
        Book book1 = new Book(UUID.fromString("1160aed8-eb2f-4fb3-92e4-43480fff64f5"), new BookTitle("Foundation"), Year.of(1951), UUID.fromString("c6625e54-d4e8-4ba0-942e-d285839527e1"));
        Book book2 = new Book(UUID.fromString("589a0b4c-93b8-4f46-8c7e-02794a8c252e"), new BookTitle("Prelude to Foundation"),  Year.of(1988), UUID.fromString("c6625e54-d4e8-4ba0-942e-d285839527e1"));
        when(searchBooksByAuthor.execute(UUID.fromString("1160aed8-eb2f-4fb3-92e4-43480fff64f5"))).thenReturn(List.of(book1, book2));

        mockMvc.perform(get("/authors/1160aed8-eb2f-4fb3-92e4-43480fff64f5/books"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                           "books": [
                             {
                               "bookIdentifier": "1160aed8-eb2f-4fb3-92e4-43480fff64f5",
                               "title": "Foundation",
                               "authorIdentifier": "c6625e54-d4e8-4ba0-942e-d285839527e1"
                             },
                             {
                               "bookIdentifier": "589a0b4c-93b8-4f46-8c7e-02794a8c252e",
                               "title": "Prelude to Foundation",
                               "authorIdentifier": "c6625e54-d4e8-4ba0-942e-d285839527e1"
                             }
                           ]
                         }"""));

    }

}