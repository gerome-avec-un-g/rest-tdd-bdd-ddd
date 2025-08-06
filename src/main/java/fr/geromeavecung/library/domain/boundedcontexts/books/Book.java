package fr.geromeavecung.library.domain.boundedcontexts.books;

import java.time.Year;
import java.util.UUID;

public record Book(ISBN isbn, BookTitle title, Year publicationDate, UUID authorIdentifier) {

}
