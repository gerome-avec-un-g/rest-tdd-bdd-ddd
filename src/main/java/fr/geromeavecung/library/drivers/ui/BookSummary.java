package fr.geromeavecung.library.drivers.ui;

import fr.geromeavecung.library.domain.boundedcontexts.authors.Author;
import fr.geromeavecung.library.domain.boundedcontexts.books.Book;
import org.springframework.hateoas.RepresentationModel;

public class BookSummary extends RepresentationModel<BookSummary> {

    private final Book book;
    private final Author author;

    public BookSummary(Book book, Author author) {
        this.book = book;
        this.author = author;
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
        return author.identifier().toString();
    }

    public String getAuthor() {
        return "%s %s".formatted(author.firstName(), author.lastName());
    }

    public String getDescription() {
        return """
                Description lorem ipsum dolor sit amet, consectetur adipiscing elit.
                Mauris sagittis pellentesque lacus eleifend lacinia...""";
    }

}
