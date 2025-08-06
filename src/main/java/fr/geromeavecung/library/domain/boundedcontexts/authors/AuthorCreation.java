package fr.geromeavecung.library.domain.boundedcontexts.authors;

import fr.geromeavecung.library.domain.boundedcontexts.shared.BusinessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorCreation {

    private final Authors authors;

    public AuthorCreation(Authors authors) {
        this.authors = authors;
    }

    public void save(Author author) {
        Optional<Author> existingAuthor = authors.find(author);
        if (existingAuthor.isPresent()) {
            throw new BusinessException("The author '%s %s' already exists".formatted(author.firstName(), author.lastName()));
        }
        authors.save(author);
    }

}
