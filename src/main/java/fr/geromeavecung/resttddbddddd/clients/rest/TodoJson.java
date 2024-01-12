package fr.geromeavecung.resttddbddddd.clients.rest;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.shared.UserIdentifier;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.todos.Status;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.todos.Title;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.todos.Todo;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.todos.TodoIdentifier;

public record TodoJson(int userId, int id, String title, boolean status) {

    public Todo toDomain() {
        return new Todo(new UserIdentifier(userId), new TodoIdentifier(id),
                new Title(title), Status.from(status));
    }

}
