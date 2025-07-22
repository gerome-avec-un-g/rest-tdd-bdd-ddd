package fr.geromeavecung.resttddbddddd.drivers.rest;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.shared.BusinessException;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.shared.ValidationException;
import fr.geromeavecung.resttddbddddd.domain.usecases.CreateAnAuthor;
import fr.geromeavecung.resttddbddddd.domain.usecases.SearchForAuthors;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = {GlobalExceptionHandler.class, AuthorController.class})
@WebMvcTest
class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CreateAnAuthor createAnAuthor;

    @MockitoBean
    private SearchForAuthors searchForAuthors;

    @Test
    void validation_exceptions_return_status_bad_request() throws Exception {
        when(createAnAuthor.execute(any())).thenThrow(new ValidationException("first name 'null' is mandatory"));

        mockMvc.perform(post("/authors")
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
                           "instance": "/authors"
                         }"""));

    }

    @Test
    void other_exceptions_return_status_internal_server_error() throws Exception {
        when(createAnAuthor.execute(any())).thenThrow(new BusinessException("author already exists"));

        mockMvc.perform(post("/authors")
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
                           "instance": "/authors"
                         }"""));

    }

    // TODO with another endpoint for instance field with url

}