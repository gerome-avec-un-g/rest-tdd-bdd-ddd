package fr.geromeavecung.resttddbddddd.clients.persistence;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.authors.Author;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.authors.Authors;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorsFromDatabase implements Authors {
    @Override
    public void save(Author author) {

    }
}
