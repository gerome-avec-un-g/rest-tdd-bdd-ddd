package fr.geromeavecung.resttddbddddd.domain.boundedcontexts.authors;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.shared.ValidationException;

import java.util.UUID;

public record Author (UUID identifier, String firstName, String lastName) {

    private static final int MAXIMUM_NAME_SIZE = 20;

    public Author {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new ValidationException("first name '%s' is mandatory".formatted(firstName));
        }
        if (firstName.length() > MAXIMUM_NAME_SIZE) {
            throw new ValidationException("first name '%s' is more than 20 characters".formatted(firstName));
        }
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new ValidationException("last name '%s' is mandatory".formatted(lastName));
        }
        if (lastName.length() > MAXIMUM_NAME_SIZE) {
            throw new ValidationException("last name '%s' is more than 20 characters".formatted(lastName));
        }
    }

    public boolean isLike(String searchTerm) {
        String fullName = firstName.toLowerCase() + " " + lastName.toLowerCase();
        return fullName.contains(searchTerm.toLowerCase());
    }
}
