package fr.geromeavecung.resttddbddddd.clients.persistence;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.authors.Author;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "AUTHORS")
public class AuthorEntity {

    public AuthorEntity() {
        // for JPA
    }

    public AuthorEntity(Author author) {
        this.identifier = author.identifier().toString();
        this.firstName = author.firstName();
        this.lastName = author.lastName();

    }

    @Id
    @Column
    private String identifier;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AuthorEntity that = (AuthorEntity) o;
        return Objects.equals(identifier, that.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(identifier);
    }

    public Author convert() {
        return new Author(UUID.fromString(identifier), firstName, lastName);
    }
}
