package fr.geromeavecung.library.drivers.ui;

import fr.geromeavecung.library.domain.boundedcontexts.books.Book;
import org.springframework.hateoas.RepresentationModel;

public class BookSummary extends RepresentationModel<BookSummary> {

    private final Book book;

    public BookSummary(Book book) {
        this.book = book;
    }

    public String getISBN() {
        return book.isbn().toString();
    }

    public String getTitle() {
        return book.title().value();
    }

    public String getPublicationDate() {
        return book.publicationDate().toString();
    }

    public String getAuthorIdentifier() {
        return book.authorIdentifier().toString();
    }

    public String getAuthor() {
        return book.authorIdentifier().toString();
    }

    public String getDescription() {
        return """
                Description lorem ipsum dolor sit amet, consectetur adipiscing elit.
                Mauris sagittis pellentesque lacus eleifend lacinia...""";
    }

}
