package fr.geromeavecung.resttddbddddd.domain.usecases;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.authors.Author;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.authors.AuthorSearchByName;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.authors.SearchForAuthorsCommand;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchForAuthors {

    private final AuthorSearchByName authorSearchByName;

    public SearchForAuthors(AuthorSearchByName authorSearchByName) {
        this.authorSearchByName = authorSearchByName;
    }

    public List<Author> execute(SearchForAuthorsCommand searchCommand) {
        return authorSearchByName.execute(searchCommand);
    }
}
