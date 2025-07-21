package fr.geromeavecung.resttddbddddd.domain.boundedcontexts.todos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodosService {

    private final Todos todos;

    @Autowired
    public TodosService(Todos todos) {
        this.todos = todos;
    }

    public Todo retrieveTodo(TodoIdentifier todoIdentifier) {
        return todos.retrieveTodo(todoIdentifier);
    }
}
