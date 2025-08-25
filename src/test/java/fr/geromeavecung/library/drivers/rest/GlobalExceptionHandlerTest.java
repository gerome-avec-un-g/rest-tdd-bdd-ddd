package fr.geromeavecung.library.drivers.rest;

import fr.geromeavecung.library.domain.boundedcontexts.shared.BusinessException;
import fr.geromeavecung.library.domain.boundedcontexts.shared.NotFoundException;
import fr.geromeavecung.library.domain.boundedcontexts.shared.ValidationException;
import fr.geromeavecung.library.domain.usecases.CreateABook;
import fr.geromeavecung.library.domain.usecases.CreateAnAuthor;
import fr.geromeavecung.library.domain.usecases.SearchABookByIdentifier;
import fr.geromeavecung.library.domain.usecases.SearchBooksByAuthor;
import fr.geromeavecung.library.domain.usecases.SearchForAuthors;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = {GlobalExceptionHandler.class, AuthorsController.class, BooksController.class})
@WebMvcTest
class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CreateAnAuthor createAnAuthor;

    @MockitoBean
    private SearchForAuthors searchForAuthors;

    @MockitoBean
    private SearchBooksByAuthor searchBooksByAuthor;

    @MockitoBean
    private CreateABook createABook;

    @MockitoBean
    private SearchABookByIdentifier searchABookByIdentifier;

    @Test
    void validation_exceptions_return_status_bad_request() throws Exception {
        when(createAnAuthor.execute(any())).thenThrow(new ValidationException("first name 'null' is mandatory"));

        mockMvc.perform(post("/api/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "firstName": "Isaac",
                                    "lastName": "Asimov"
                                }"""))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().json("""
                        {
                           "type": "about:blank",
                           "title": "Bad Request",
                           "status": 400,
                           "detail": "first name 'null' is mandatory",
                           "instance": "/api/authors"
                         }"""));

    }

    @Test
    void not_found_exceptions_return_status_not_found() throws Exception {
        when(searchForAuthors.execute(any())).thenThrow(new NotFoundException("Authors named 'Asimov' not found"));

        mockMvc.perform(get("/api/authors?searchTerm=Asimov"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().json("""
                        {
                           "type": "about:blank",
                           "title": "Not Found",
                           "status": 404,
                           "detail": "Authors named 'Asimov' not found",
                           "instance": "/api/authors"
                         }"""));

    }

    @Test
    void other_exceptions_return_status_internal_server_error() throws Exception {
        when(createAnAuthor.execute(any())).thenThrow(new BusinessException("author already exists"));

        mockMvc.perform(post("/api/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "firstName": "Isaac",
                                    "lastName": "Asimov"
                                }"""))
                .andDo(print())
                .andExpect(status().isInternalServerError())
                .andExpect(content().json("""
                        {
                           "type": "about:blank",
                           "title": "Internal Server Error",
                           "status": 500,
                           "detail": "author already exists",
                           "instance": "/api/authors"
                         }"""));

    }

    @Test
    void instance_depends_on_endpoint() throws Exception {
        when(createABook.execute(any())).thenThrow(new BusinessException("book already exists"));

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
                .andExpect(status().isInternalServerError())
                .andExpect(content().json("""
                        {
                           "type": "about:blank",
                           "title": "Internal Server Error",
                           "status": 500,
                           "detail": "book already exists",
                           "instance": "/api/books"
                         }"""));

    }

}