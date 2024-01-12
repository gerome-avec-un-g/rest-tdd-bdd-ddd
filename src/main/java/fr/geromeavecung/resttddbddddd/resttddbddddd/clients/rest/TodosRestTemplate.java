package fr.geromeavecung.resttddbddddd.resttddbddddd.clients.rest;

import fr.geromeavecung.resttddbddddd.resttddbddddd.domain.boundedcontexts.todos.Todo;
import fr.geromeavecung.resttddbddddd.resttddbddddd.domain.boundedcontexts.todos.TodoIdentifier;
import fr.geromeavecung.resttddbddddd.resttddbddddd.domain.boundedcontexts.todos.Todos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Repository
public class TodosRestTemplate implements Todos {

    private final RestTemplate restTemplate;

    @Autowired
    public TodosRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Todo retrieveTodo(TodoIdentifier todoIdentifier) {
        TodoJson todoJson = restTemplate.getForObject("/todos/" + todoIdentifier.value(), TodoJson.class);
        if (todoJson == null) {
            return null;
        }
        return todoJson.toDomain();
    }
}
