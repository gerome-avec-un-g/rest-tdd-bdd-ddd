package fr.geromeavecung.resttddbddddd.domain.boundedcontexts.books;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.shared.ValidationException;

import java.util.UUID;

public record Book(UUID bookIdentifier, String title, UUID authorIdentifier) {

    private static final int MAXIMUM_TITLE_SIZE = 30;

    public Book {
        if (title == null || title.trim().isEmpty()) {
            throw new ValidationException("title '%s' is mandatory".formatted(title));
        }
        if (title.length() > MAXIMUM_TITLE_SIZE) {
            throw new ValidationException("title '%s' is more than 30 characters".formatted(title));
        }
    }

}
