package fr.geromeavecung.library.domain.boundedcontexts.books;

import fr.geromeavecung.library.domain.boundedcontexts.shared.ValidationException;

public record BookTitle(String value) {

    private static final int MAXIMUM_TITLE_SIZE = 30;

    public BookTitle(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new ValidationException("title '%s' is mandatory".formatted(value));
        }
        if (value.length() > MAXIMUM_TITLE_SIZE) {
            throw new ValidationException("title '%s' is more than 30 characters".formatted(value));
        }
        this.value = value.trim();
    }

}
