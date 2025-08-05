package fr.geromeavecung.resttddbddddd.drivers.rest;

import fr.geromeavecung.resttddbddddd.domain.usecases.CreateABookCommand;

public record BookCreationRequest(String title, String publicationDate, String authorIdentifier) {

    CreateABookCommand convert() {
        return new CreateABookCommand(title, publicationDate, authorIdentifier);
    }

}
