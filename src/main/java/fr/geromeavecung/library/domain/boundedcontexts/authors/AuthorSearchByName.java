package fr.geromeavecung.library.domain.boundedcontexts.authors;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorSearchByName {

    private final Authors authors;

    public AuthorSearchByName(Authors authors) {
        this.authors = authors;
    }

    public List<Author> execute(SearchForAuthorsCommand searchCommand) {
        return authors.findAll().stream()
                .filter(author -> author.isLike(searchCommand.searchTerm()))
                .toList();
    }

    public List<Author> findAll() {
        return authors.findAll();
    }

    public Optional<Author> find(UUID identifier) {
        return authors.find(identifier);
    }
}
