package fr.geromeavecung.resttddbddddd.drivers.rest;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.books.Book;

public record BookCreationResponse(String bookIdentifier, String title, String authorIdentifier) {

    public BookCreationResponse(Book book) {
        this(book.bookIdentifier().toString(), book.title().value(), book.authorIdentifier().toString());
    }
}
