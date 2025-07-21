package fr.geromeavecung.resttddbddddd.domain.boundedcontexts.todos;

public interface Todos {

    // FIXME Todos for DDD-Repository or for List<Todo> (maybe this has another semantic name) ?
    Todo retrieveTodo(TodoIdentifier todoIdentifier);

}
