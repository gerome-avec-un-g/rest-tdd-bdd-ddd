package fr.geromeavecung.library.drivers.rest;

import fr.geromeavecung.library.domain.boundedcontexts.authors.Author;
import fr.geromeavecung.library.domain.boundedcontexts.authors.SearchForAuthorsCommand;
import fr.geromeavecung.library.domain.boundedcontexts.books.Book;
import fr.geromeavecung.library.domain.boundedcontexts.books.BookTitle;
import fr.geromeavecung.library.domain.boundedcontexts.books.ISBN;
import fr.geromeavecung.library.domain.boundedcontexts.books.PublicationDate;
import fr.geromeavecung.library.domain.usecases.CreateAnAuthor;
import fr.geromeavecung.library.domain.usecases.CreateAnAuthorCommand;
import fr.geromeavecung.library.domain.usecases.SearchBooksByAuthor;
import fr.geromeavecung.library.domain.usecases.SearchForAuthors;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

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

    private static final Book FOUNDATION = new Book(new ISBN("978-0553293357"), new BookTitle("Foundation"), PublicationDate.read(1951), UUID.fromString("c6625e54-d4e8-4ba0-942e-d285839527e1"));
    private static final Book PRELUDE_TO_FOUNDATION = new Book(new ISBN("978-0385233132"), new BookTitle("Prelude to Foundation"), PublicationDate.read(1988), UUID.fromString("c6625e54-d4e8-4ba0-942e-d285839527e1"));

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
        when(searchBooksByAuthor.execute(UUID.fromString("c6625e54-d4e8-4ba0-942e-d285839527e1"))).thenReturn(List.of(FOUNDATION, PRELUDE_TO_FOUNDATION));

        mockMvc.perform(get("/authors/c6625e54-d4e8-4ba0-942e-d285839527e1/books"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                          "books": [
                            {
                              "isbn": "978-0553293357",
                              "title": "Foundation",
                              "authorIdentifier": "c6625e54-d4e8-4ba0-942e-d285839527e1",
                              "links": [
                                {
                                  "rel": "self",
                                  "href": "http://localhost/books/978-0553293357"
                                }
                              ]
                            },
                            {
                              "isbn": "978-0385233132",
                              "title": "Prelude to Foundation",
                              "authorIdentifier": "c6625e54-d4e8-4ba0-942e-d285839527e1",
                              "links": [
                                {
                                  "rel": "self",
                                  "href": "http://localhost/books/978-0385233132"
                                }
                              ]
                            }
                          ]
                        }"""));

    }

}