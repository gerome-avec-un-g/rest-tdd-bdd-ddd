package fr.geromeavecung.resttddbddddd.clients.rest;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.shared.UserIdentifier;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.todos.Status;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.todos.Title;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.todos.Todo;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.todos.TodoIdentifier;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(TodosFromRest.class)
@AutoConfigureWebClient(registerRestTemplate = true)
class TodosFromRestTest {

    @Autowired
    private TodosFromRest restTemplate;

    @Autowired
    private MockRestServiceServer mockServer;

    @Value("classpath:json/todo.json")
    private Resource todo;

    @Test
    void retrieve_1_todo() {
        var expected = new Todo(new UserIdentifier(1), new TodoIdentifier(1),
                new Title("delectus aut autem"), Status.PENDING);
        mockServer.expect(requestTo("/todos/1"))
                .andRespond(withSuccess(todo, MediaType.APPLICATION_JSON));

        Todo actual = restTemplate.retrieveTodo(new TodoIdentifier(1));

        mockServer.verify();
        assertThat(actual).isEqualTo(expected);
    }

}