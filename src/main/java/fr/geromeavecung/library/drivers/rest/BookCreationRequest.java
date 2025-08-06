package fr.geromeavecung.library.drivers.rest;

import fr.geromeavecung.library.domain.usecases.CreateABookCommand;

public record BookCreationRequest(String isbn, String title, String publicationDate, String authorIdentifier) {

    CreateABookCommand convert() {
        return new CreateABookCommand(isbn, title, publicationDate, authorIdentifier);
    }

}
