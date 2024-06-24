package fr.geromeavecung.resttddbddddd.drivers.cucumber;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.geromeavecung.resttddbddddd.clients.rest.TodoJson;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.todos.Todo;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.todos.TodoIdentifier;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.todos.Todos;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TodosInMemory implements Todos {

    private final List<TodoJson> values;

    public TodosInMemory() throws JsonProcessingException {
        this.values= new ObjectMapper().readValue("""
                [{
                  "userId": 1,
                  "id": 1,
                  "title": "delectus aut autem",
                  "completed": false
                }]""", new ObjectMapper().getTypeFactory().constructCollectionType(List.class, TodoJson.class));
    }

    @Override
    public Todo retrieveTodo(TodoIdentifier todoIdentifier) {
        return values.stream()
                .filter(todoJson -> todoJson.id() == todoIdentifier.value())
                .map(TodoJson::toDomain)
                .findFirst().orElse(null);
    }
}
