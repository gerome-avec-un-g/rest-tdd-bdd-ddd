package fr.geromeavecung.resttddbddddd.domain.usecases;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.authors.Author;

import java.util.UUID;

public record CreateAnAuthorCommand(String firstName, String lastName) {

    public Author convert(UUID identifier) {
        return new Author(identifier, firstName, lastName);
    }
}
