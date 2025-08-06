package fr.geromeavecung.library.drivers.rest;

import fr.geromeavecung.library.domain.boundedcontexts.books.Book;
import org.springframework.hateoas.RepresentationModel;

public class BookCreationResponse extends RepresentationModel<BookCreationResponse> {

    private final String isbn;
    private final String title;
    private final String publicationDate;
    private final String authorIdentifier;

    public static BookCreationResponse create(Book book) {
        return new BookCreationResponse(book.isbn().toString(), book.title().value(), book.publicationDate().toString(), book.authorIdentifier().toString());
    }

    private BookCreationResponse(String isbn, String title, String publicationDate, String authorIdentifier) {
        this.isbn = isbn;
        this.title = title;
        this.publicationDate = publicationDate;
        this.authorIdentifier = authorIdentifier;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public String getAuthorIdentifier() {
        return authorIdentifier;
    }

}
