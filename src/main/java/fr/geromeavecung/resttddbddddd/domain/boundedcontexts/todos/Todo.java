package fr.geromeavecung.resttddbddddd.domain.boundedcontexts.todos;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.shared.UserIdentifier;

public record Todo(UserIdentifier userIdentifier, TodoIdentifier todoIdentifier, Title title, Status status) {
}
