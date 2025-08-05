package fr.geromeavecung.resttddbddddd.domain.usecases;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.authors.Author;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.authors.AuthorSearchByName;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.authors.SearchForAuthorsCommand;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.shared.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchForAuthors {

    private final AuthorSearchByName authorSearchByName;

    public SearchForAuthors(AuthorSearchByName authorSearchByName) {
        this.authorSearchByName = authorSearchByName;
    }

    public List<Author> execute(SearchForAuthorsCommand searchCommand) {
        List<Author> authors = authorSearchByName.execute(searchCommand);
        if (authors.isEmpty()) {
            throw new NotFoundException("Authors named '%s' not found".formatted(searchCommand.searchTerm()));
        }
        return authors;
    }
}
