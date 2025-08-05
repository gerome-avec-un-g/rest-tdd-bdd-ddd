package fr.geromeavecung.resttddbddddd.domain.usecases;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.books.Book;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.books.BookTitle;

import java.time.Year;
import java.util.UUID;

public record CreateABookCommand(String title, String publicationDate, String authorIdentifier) {
    public Book convert(UUID bookIdentifier) {
        return new Book(bookIdentifier, new BookTitle(title), Year.parse(publicationDate), UUID.fromString(authorIdentifier));
    }
}
