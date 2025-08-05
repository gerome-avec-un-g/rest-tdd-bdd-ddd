package fr.geromeavecung.resttddbddddd.drivers.rest;

import fr.geromeavecung.resttddbddddd.domain.usecases.CreateABookCommand;

public record BookCreationRequest(String title, String authorIdentifier) {

    CreateABookCommand convert() {
        return new CreateABookCommand(title, authorIdentifier);
    }

}
