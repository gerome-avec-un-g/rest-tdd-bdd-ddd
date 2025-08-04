package fr.geromeavecung.resttddbddddd.domain.boundedcontexts.authors;

import fr.geromeavecung.resttddbddddd.domain.usecases.SearchForAuthorsCommand;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
