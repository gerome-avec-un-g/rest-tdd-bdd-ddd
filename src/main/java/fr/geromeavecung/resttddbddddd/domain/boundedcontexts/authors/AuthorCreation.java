package fr.geromeavecung.resttddbddddd.domain.boundedcontexts.authors;

import org.springframework.stereotype.Service;

@Service
public class AuthorCreation {

    private final Authors authors;

    public AuthorCreation(Authors authors) {
        this.authors = authors;
    }

    public void save(Author author) {
        authors.save(author);
    }

}
