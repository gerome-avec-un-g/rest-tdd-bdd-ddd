package fr.geromeavecung.library.clients.persistence;

import fr.geromeavecung.library.domain.boundedcontexts.authors.Author;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "AUTHORS")
public class AuthorEntity {

    public AuthorEntity() {
        // for JPA
    }

    public AuthorEntity(Author author) {
        this.identifier = author.identifier();
        this.firstName = author.firstName();
        this.lastName = author.lastName();
    }

    @Id
    @Column(name="IDENTIFIER")
    private UUID identifier;

    @Column(name="FIRST_NAME")
    private String firstName;

    @Column(name="LAST_NAME")
    private String lastName;

    public Author convert() {
        return new Author(identifier, firstName, lastName);
    }
}
