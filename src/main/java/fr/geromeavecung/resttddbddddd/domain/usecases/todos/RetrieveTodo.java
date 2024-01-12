package fr.geromeavecung.resttddbddddd.domain.usecases.todos;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.todos.Todo;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.todos.TodoIdentifier;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.todos.TodoPresentation;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.todos.TodosService;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.users.User;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RetrieveTodo {

    private final TodosService todosService;

    private final UsersService usersService;

    @Autowired
    public RetrieveTodo(TodosService todosService, UsersService usersService) {
        this.todosService = todosService;
        this.usersService = usersService;
    }

    public TodoPresentation retrieveTodo(TodoIdentifier todoIdentifier) {
        Todo todo = todosService.retrieveTodo(todoIdentifier);
        User user = usersService.retrieveTodo(todo.userIdentifier());
        return new TodoPresentation(Integer.toString(todo.userIdentifier().value()),
                Integer.toString(todo.todoIdentifier().value()),
                todo.title().value(), todo.status().toString(),
                user.name().value(), user.address().city().value());
    }

}
