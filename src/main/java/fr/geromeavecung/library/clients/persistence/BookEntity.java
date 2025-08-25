package fr.geromeavecung.library.clients.persistence;

import fr.geromeavecung.library.domain.boundedcontexts.books.Book;
import fr.geromeavecung.library.domain.boundedcontexts.books.BookTitle;
import fr.geromeavecung.library.domain.boundedcontexts.books.ISBN;
import fr.geromeavecung.library.domain.boundedcontexts.books.PublicationDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "BOOKS")
public class BookEntity {

    public BookEntity() {
        // for JPA
    }

    public BookEntity(Book book) {
        this.isbn = book.isbn().value();
        this.title = book.title().value();
        this.publicationDate = book.publicationDate().year().getValue();
        this.authorIdentifier = book.authorIdentifier();
    }

    @Id
    @Column(name="ISBN")
    private String isbn;

    @Column(name="TITLE")
    private String title;

    @Column(name="PUBLICATION_DATE")
    private int publicationDate;

    @Column(name="AUTHOR_IDENTIFIER")
    private UUID authorIdentifier;

    public Book convert() {
        return new Book(new ISBN(isbn), new BookTitle(title), PublicationDate.read(publicationDate), authorIdentifier);
    }

}
