package fr.geromeavecung.library.domain.boundedcontexts.authors;

import fr.geromeavecung.library.domain.boundedcontexts.shared.BusinessException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthorExists {

    private final Authors authors;

    public AuthorExists(Authors authors) {
        this.authors = authors;
    }

    public void execute(UUID identifier) {
        authors.find(identifier)
                .orElseThrow(() -> new BusinessException("author identifier '%s' doesn't exists".formatted(identifier)));
    }
}
