package fr.geromeavecung.resttddbddddd.clients.persistence;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.books.Book;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.books.BookTitle;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "BOOKS")
public class BookEntity {

    public BookEntity() {
        // for JPA
    }

    public BookEntity(Book book) {
        this.bookIdentifier = book.bookIdentifier();
        this.title = book.title().value();
        this.authorIdentifier = book.authorIdentifier();
    }

    @Id
    @Column
    private UUID bookIdentifier;

    @Column
    private String title;

    @Column
    private UUID authorIdentifier;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BookEntity that = (BookEntity) o;
        return Objects.equals(bookIdentifier, that.bookIdentifier);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(bookIdentifier);
    }

    public Book convert() {
        return new Book(bookIdentifier, new BookTitle(title), authorIdentifier);
    }

}
