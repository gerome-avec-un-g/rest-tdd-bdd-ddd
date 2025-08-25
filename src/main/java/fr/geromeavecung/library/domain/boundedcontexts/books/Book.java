package fr.geromeavecung.library.domain.boundedcontexts.books;

import java.util.UUID;

public record Book(ISBN isbn, BookTitle title, PublicationDate publicationDate, UUID authorIdentifier) {

}
