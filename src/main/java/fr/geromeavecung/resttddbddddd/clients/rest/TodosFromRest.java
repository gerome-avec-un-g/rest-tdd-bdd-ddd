package fr.geromeavecung.resttddbddddd.clients.rest;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.todos.Todo;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.todos.TodoIdentifier;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.todos.Todos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class TodosFromRest implements Todos {

    private final RestTemplate restTemplate;

    @Autowired
    public TodosFromRest(RestTemplate jsonPlaceholderRestTemplate) {
        this.restTemplate = jsonPlaceholderRestTemplate;
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
