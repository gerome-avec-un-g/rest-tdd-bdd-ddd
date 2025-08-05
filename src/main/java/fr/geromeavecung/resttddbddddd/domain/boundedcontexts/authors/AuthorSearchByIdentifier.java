package fr.geromeavecung.resttddbddddd.domain.boundedcontexts.authors;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.shared.BusinessException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthorSearchByIdentifier {

    private final Authors authors;

    public AuthorSearchByIdentifier(Authors authors) {
        this.authors = authors;
    }

    public Author execute(UUID identifier) {
        // TODO just validate?
        return authors.find(identifier)
                .orElseThrow(() -> new BusinessException("author identifier '%s' doesn't exists".formatted(identifier)));
    }
}
