package fr.geromeavecung.resttddbddddd.drivers.rest;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.books.Book;
import org.springframework.hateoas.RepresentationModel;

public class BookSummaryResponse extends RepresentationModel<BookSummaryResponse> {

    private final String bookIdentifier;
    private final String title;
    private final String authorIdentifier;

    public static BookSummaryResponse create(Book book) {
        return new BookSummaryResponse(book.bookIdentifier().toString(), book.title().value(), book.authorIdentifier().toString());
    }

    private BookSummaryResponse(String bookIdentifier, String title, String authorIdentifier) {
        this.bookIdentifier = bookIdentifier;
        this.title = title;
        this.authorIdentifier = authorIdentifier;
    }

    public String getBookIdentifier() {
        return bookIdentifier;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthorIdentifier() {
        return authorIdentifier;
    }

}
