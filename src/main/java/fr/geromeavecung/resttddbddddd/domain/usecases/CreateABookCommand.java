package fr.geromeavecung.resttddbddddd.domain.usecases;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.books.Book;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.books.BookTitle;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.books.ISBN;

import java.time.Year;
import java.util.UUID;

public record CreateABookCommand(String isbn, String title, String publicationDate, String authorIdentifier) {
    public Book convert() {
        return new Book(new ISBN(isbn), new BookTitle(title), Year.parse(publicationDate), UUID.fromString(authorIdentifier));
    }
}
