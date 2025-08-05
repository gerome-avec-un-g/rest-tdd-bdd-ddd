package fr.geromeavecung.resttddbddddd.drivers.rest;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.books.Book;
import org.springframework.hateoas.RepresentationModel;

public class BookSummaryResponse extends RepresentationModel<BookSummaryResponse> {

    private final String isbn;
    private final String title;
    private final String authorIdentifier;

    public static BookSummaryResponse create(Book book) {
        return new BookSummaryResponse(book.isbn().toString(), book.title().value(), book.authorIdentifier().toString());
    }

    private BookSummaryResponse(String isbn, String title, String authorIdentifier) {
        this.isbn = isbn;
        this.title = title;
        this.authorIdentifier = authorIdentifier;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthorIdentifier() {
        return authorIdentifier;
    }

}
