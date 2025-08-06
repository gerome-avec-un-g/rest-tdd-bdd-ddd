package fr.geromeavecung.library.drivers.rest;

import fr.geromeavecung.library.domain.boundedcontexts.authors.Author;

import java.util.List;

public record AuthorsSearchResponse(List<Author> authors) {

}
