package fr.geromeavecung.library.domain.boundedcontexts.books;

import fr.geromeavecung.library.domain.boundedcontexts.shared.ValidationException;

// International Standard Book Number
public record ISBN(String value) {

    private static final int MAXIMUM_ISBN_SIZE = 14;

    // TODO format

    public ISBN(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new ValidationException("ISBN '%s' is mandatory".formatted(value));
        }
        if (value.length() > MAXIMUM_ISBN_SIZE) {
            throw new ValidationException("ISBN '%s' is more than %s characters".formatted(value, MAXIMUM_ISBN_SIZE));
        }
        this.value = value.trim();
    }

    @Override
    public String toString() {
        return value;
    }
}
