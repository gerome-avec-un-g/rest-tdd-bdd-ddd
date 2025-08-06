package fr.geromeavecung.library.drivers.rest;

import fr.geromeavecung.library.domain.usecases.CreateAnAuthorCommand;

public record AuthorCreationRequest(String firstName, String lastName) {

    CreateAnAuthorCommand convert() {
        return new CreateAnAuthorCommand(firstName, lastName);
    }

}
