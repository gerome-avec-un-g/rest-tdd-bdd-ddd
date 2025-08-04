package fr.geromeavecung.resttddbddddd.drivers.rest;

import fr.geromeavecung.resttddbddddd.domain.usecases.CreateAnAuthorCommand;

public record AuthorCreationRequest(String firstName, String lastName) {

    CreateAnAuthorCommand convert() {
        return new CreateAnAuthorCommand(firstName, lastName);
    }

}
