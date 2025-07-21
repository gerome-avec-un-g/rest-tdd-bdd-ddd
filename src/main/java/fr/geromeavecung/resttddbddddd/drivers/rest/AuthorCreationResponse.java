package fr.geromeavecung.resttddbddddd.drivers.rest;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.authors.Author;

public record AuthorCreationResponse(String identifier, String firstName, String lastName) {


    public AuthorCreationResponse(Author author) {
        this(author.identifier().toString(), author.firstName(), author.lastName());
    }
}
