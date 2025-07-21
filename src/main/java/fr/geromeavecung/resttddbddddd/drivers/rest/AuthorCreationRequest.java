package fr.geromeavecung.resttddbddddd.drivers.rest;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.authors.AuthorCreationCommand;

public record AuthorCreationRequest(String firstName, String lastName) {

    AuthorCreationCommand convert() {
        return new AuthorCreationCommand(firstName, lastName);
    }

}
