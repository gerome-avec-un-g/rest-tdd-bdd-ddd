package fr.geromeavecung.library.clients.persistence;

import fr.geromeavecung.library.domain.boundedcontexts.authors.Author;

import java.util.UUID;

public record AuthorEntityProjection(UUID identifier, String firstName, String lastName) {

    public Author convert() {
        return new Author(identifier, firstName, lastName);
    }

}
