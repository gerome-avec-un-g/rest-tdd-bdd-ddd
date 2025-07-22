package fr.geromeavecung.resttddbddddd.domain.boundedcontexts.authors;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorSearch {

    private final Authors authors;

    public AuthorSearch(Authors authors) {
        this.authors = authors;
    }

    public List<Author> execute(AuthorsSearchCommand searchCommand) {
        return authors.findAll().stream()
                .filter(author -> author.isLike(searchCommand.searchTerm()))
                .toList();
    }
}
