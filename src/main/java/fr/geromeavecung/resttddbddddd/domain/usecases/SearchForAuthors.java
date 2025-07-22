package fr.geromeavecung.resttddbddddd.domain.usecases;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.authors.Author;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.authors.AuthorSearch;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.authors.AuthorsSearchCommand;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchForAuthors {

    private final AuthorSearch authorSearch;

    public SearchForAuthors(AuthorSearch authorSearch) {
        this.authorSearch = authorSearch;
    }

    public List<Author> execute(AuthorsSearchCommand searchCommand) {
        return authorSearch.execute(searchCommand);
    }
}
