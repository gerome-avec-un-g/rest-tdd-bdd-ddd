package fr.geromeavecung.resttddbddddd.domain.boundedcontexts.authors;

import java.util.UUID;

public record AuthorCreationCommand(String firstName, String lastName) {

    public Author convert(UUID identifier) {
        return new Author(identifier, firstName, lastName);
    }
}
