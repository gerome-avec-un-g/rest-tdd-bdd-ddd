package fr.geromeavecung.library.drivers.ui;

import fr.geromeavecung.library.domain.boundedcontexts.authors.Author;
import org.springframework.hateoas.RepresentationModel;

public class AuthorDetails extends RepresentationModel<AuthorDetails> {

    private final Author author;

    public AuthorDetails(Author author) {
        this.author = author;
    }

    public String getIdentifier() {
        return author.identifier().toString();
    }

    public String getFirstName() {
        return author.firstName();
    }

    public String getLastName() {
        return author.lastName();
    }
}
