package fr.geromeavecung.resttddbddddd.domain.boundedcontexts.authors;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.shared.ValidationException;

public record AuthorsSearchCommand(String searchTerm) {

    private static final int MINIMUM_NAME_SIZE = 3;
    private static final int MAXIMUM_NAME_SIZE = 20;

    public AuthorsSearchCommand {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            throw new ValidationException("search term '%s' is mandatory".formatted(searchTerm));
        }
        if (searchTerm.length() < MINIMUM_NAME_SIZE || searchTerm.length() > MAXIMUM_NAME_SIZE) {
            throw new ValidationException("search term '%s' must be between %s and %s characters".formatted(searchTerm, MINIMUM_NAME_SIZE, MAXIMUM_NAME_SIZE));
        }
    }
    
}
