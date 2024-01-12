package fr.geromeavecung.resttddbddddd.resttddbddddd.domain.boundedcontexts.todos;

import fr.geromeavecung.resttddbddddd.resttddbddddd.domain.boundedcontexts.shared.UserIdentifier;

public record Todo(UserIdentifier userIdentifier, TodoIdentifier todoIdentifier, Title title, Status status) {
}
