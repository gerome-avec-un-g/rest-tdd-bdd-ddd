package fr.geromeavecung.resttddbddddd.domain.usecases.todos;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.todos.Todo;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.todos.TodoIdentifier;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.todos.TodoPresentation;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.todos.TodosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RetrieveTodo {

    private final TodosService todosService;

    @Autowired
    public RetrieveTodo(TodosService todosService) {
        this.todosService = todosService;
    }


    public TodoPresentation retrieveTodo(TodoIdentifier todoIdentifier) {
        Todo todo = todosService.retrieveTodo(todoIdentifier);
        return new TodoPresentation(Integer.toString(todo.userIdentifier().value())
                , Integer.toString(todo.todoIdentifier().value()),
                todo.title().value(), todo.status().toString());
    }

}
