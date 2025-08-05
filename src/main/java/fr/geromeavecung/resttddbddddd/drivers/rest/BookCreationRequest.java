package fr.geromeavecung.resttddbddddd.drivers.rest;

import fr.geromeavecung.resttddbddddd.domain.usecases.CreateABookCommand;

public record BookCreationRequest(String isbn, String title, String publicationDate, String authorIdentifier) {

    CreateABookCommand convert() {
        return new CreateABookCommand(isbn, title, publicationDate, authorIdentifier);
    }

}
