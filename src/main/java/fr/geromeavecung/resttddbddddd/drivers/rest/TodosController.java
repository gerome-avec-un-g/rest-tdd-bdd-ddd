package fr.geromeavecung.resttddbddddd.drivers.rest;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.todos.TodoIdentifier;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.todos.TodoDetails;
import fr.geromeavecung.resttddbddddd.domain.usecases.todos.RetrieveTodo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/todos")
public class TodosController {

    private final RetrieveTodo retrieveTodo;

    @Autowired
    public TodosController(RetrieveTodo retrieveTodo) {
        this.retrieveTodo = retrieveTodo;
    }

    @GetMapping("/{todoIdentifier}")
    public ResponseEntity<TodoDetails> getTodo(@PathVariable int todoIdentifier) {
        TodoDetails todoDetails = retrieveTodo.retrieveTodo(new TodoIdentifier(todoIdentifier));
        return ResponseEntity.ok(todoDetails);
    }

    // TODO request body
    // TODO request param
}
