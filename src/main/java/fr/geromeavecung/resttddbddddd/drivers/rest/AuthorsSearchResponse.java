package fr.geromeavecung.resttddbddddd.drivers.rest;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.authors.Author;

import java.util.List;

public record AuthorsSearchResponse(List<Author> authors) {

}
