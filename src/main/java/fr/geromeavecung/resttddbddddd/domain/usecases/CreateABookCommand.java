package fr.geromeavecung.resttddbddddd.domain.usecases;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.books.Book;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.books.BookTitle;

import java.util.UUID;

public record CreateABookCommand(String title, String authorIdentifier) {
    public Book convert(UUID bookIdentifier) {
        return new Book(bookIdentifier, new BookTitle(title), UUID.fromString(authorIdentifier));
    }
}
