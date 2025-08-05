package fr.geromeavecung.resttddbddddd.domain.boundedcontexts.books;

import java.util.UUID;

public record Book(UUID bookIdentifier, BookTitle title, UUID authorIdentifier) {

}
