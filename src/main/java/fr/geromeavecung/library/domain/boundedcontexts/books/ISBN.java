package fr.geromeavecung.library.domain.boundedcontexts.books;

import fr.geromeavecung.library.domain.boundedcontexts.shared.ValidationException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// International Standard Book Number
public record ISBN(String value) {

    private static final Pattern ISBN_PATTERN = Pattern.compile("[0-9]{3}-[0-9]{10}");

    public ISBN {
        if (value == null) {
            throw new ValidationException("ISBN is mandatory");
        }
        Matcher matcher = ISBN_PATTERN.matcher(value);
        if (!matcher.matches()) {
            throw new ValidationException("ISBN '%s' doesn't correspond to format 000-0000000000".formatted(value));
        }
    }

    @Override
    public String toString() {
        return value;
    }
}
