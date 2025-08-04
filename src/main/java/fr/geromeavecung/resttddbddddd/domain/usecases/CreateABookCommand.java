package fr.geromeavecung.resttddbddddd.domain.usecases;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.books.Book;

import java.util.UUID;

public record CreateABookCommand(String title, String authorIdentifier) {
    public Book convert(UUID bookIdentifier) {
        return new Book(bookIdentifier, title, UUID.fromString(authorIdentifier));
    }
}
