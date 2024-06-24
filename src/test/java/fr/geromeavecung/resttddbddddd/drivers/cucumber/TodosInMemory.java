package fr.geromeavecung.resttddbddddd.drivers.cucumber;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.geromeavecung.resttddbddddd.clients.rest.TodoJson;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.todos.Todo;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.todos.TodoIdentifier;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.todos.Todos;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TodosInMemory implements Todos {

    @Value("classpath:json/todos.json")
    private Resource todos;

    @Value("on")
    private String test;

    private final List<TodoJson> values;

    public TodosInMemory() throws JsonProcessingException {
        System.out.println("ckeck "+test);
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
