package fr.geromeavecung.library.domain.usecases;

import fr.geromeavecung.library.domain.boundedcontexts.books.Book;
import fr.geromeavecung.library.domain.boundedcontexts.books.BookTitle;
import fr.geromeavecung.library.domain.boundedcontexts.books.ISBN;
import fr.geromeavecung.library.domain.boundedcontexts.books.PublicationDate;

import java.time.Year;
import java.util.UUID;

public record CreateABookCommand(String isbn, String title, String publicationDate, String authorIdentifier) {

    public Book convert(Year currentYear) {
        return new Book(new ISBN(isbn), new BookTitle(title), PublicationDate.create(publicationDate, currentYear), UUID.fromString(authorIdentifier));
    }

}
