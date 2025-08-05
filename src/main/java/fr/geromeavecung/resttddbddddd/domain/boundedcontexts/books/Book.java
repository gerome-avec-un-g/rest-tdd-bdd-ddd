package fr.geromeavecung.resttddbddddd.domain.boundedcontexts.books;

import java.time.Year;
import java.util.UUID;

public record Book(UUID bookIdentifier, BookTitle title, Year publicationDate, UUID authorIdentifier) {
        // TODO replace bookIdentifier by ISBN
}
