package fr.geromeavecung.resttddbddddd.resttddbddddd.drivers.rest;

import fr.geromeavecung.resttddbddddd.resttddbddddd.domain.boundedcontexts.todos.TodoIdentifier;
import fr.geromeavecung.resttddbddddd.resttddbddddd.domain.boundedcontexts.todos.TodoPresentation;
import fr.geromeavecung.resttddbddddd.resttddbddddd.domain.usecases.todos.RetrieveTodo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class TodosControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RetrieveTodo retrieveTodo;

    @Test
    void getTodo() throws Exception {
        var todo = new TodoPresentation("1", "1",
                "delectus aut autem", "PENDING");
        when(retrieveTodo.retrieveTodo(new TodoIdentifier(1))).thenReturn(todo);

        mockMvc.perform(get("/todos/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"userIdentifier\":\"1\",\"todoIdentifier\":\"1\",\"title\":\"delectus aut autem\",\"status\":\"PENDING\"}")));
    }

}