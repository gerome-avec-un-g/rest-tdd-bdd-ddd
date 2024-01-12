package fr.geromeavecung.resttddbddddd.resttddbddddd.domain.usecases.todos;

import fr.geromeavecung.resttddbddddd.resttddbddddd.domain.boundedcontexts.todos.Todo;
import fr.geromeavecung.resttddbddddd.resttddbddddd.domain.boundedcontexts.todos.TodoIdentifier;
import fr.geromeavecung.resttddbddddd.resttddbddddd.domain.boundedcontexts.todos.TodoPresentation;
import fr.geromeavecung.resttddbddddd.resttddbddddd.domain.boundedcontexts.todos.Todos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RetrieveTodo {

    private final Todos todos;

    @Autowired
    public RetrieveTodo(Todos todos) {
        this.todos = todos;
    }

    public TodoPresentation retrieveTodo(TodoIdentifier todoIdentifier) {
        Todo todo = todos.retrieveTodo(todoIdentifier);
        return new TodoPresentation(Integer.toString(todo.userIdentifier().value())
                , Integer.toString(todo.todoIdentifier().value()),
                todo.title().value(), todo.status().toString());
    }

}
