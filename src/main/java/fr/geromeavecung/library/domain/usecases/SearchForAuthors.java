package fr.geromeavecung.library.domain.usecases;

import fr.geromeavecung.library.domain.boundedcontexts.authors.Author;
import fr.geromeavecung.library.domain.boundedcontexts.authors.AuthorSearchByName;
import fr.geromeavecung.library.domain.boundedcontexts.authors.SearchForAuthorsCommand;
import fr.geromeavecung.library.domain.boundedcontexts.shared.BusinessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SearchForAuthors {

    private final AuthorSearchByName authorSearchByName;

    public SearchForAuthors(AuthorSearchByName authorSearchByName) {
        this.authorSearchByName = authorSearchByName;
    }

    public List<Author> execute(SearchForAuthorsCommand searchCommand) {
        return authorSearchByName.execute(searchCommand);
    }

    public List<Author> findAll() {
        return authorSearchByName.findAll();
    }

    public Author findByIdentifier(UUID identifier) {
        return authorSearchByName.find(identifier)
                .orElseThrow(() -> new BusinessException("identifier not found %s".formatted(identifier)));
    }
}
