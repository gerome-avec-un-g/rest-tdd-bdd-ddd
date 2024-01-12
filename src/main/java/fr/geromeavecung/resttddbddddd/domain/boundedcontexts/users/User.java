package fr.geromeavecung.resttddbddddd.domain.boundedcontexts.users;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.shared.UserIdentifier;

public record User(UserIdentifier userIdentifier, Name name, Email email, Address address) {
}
