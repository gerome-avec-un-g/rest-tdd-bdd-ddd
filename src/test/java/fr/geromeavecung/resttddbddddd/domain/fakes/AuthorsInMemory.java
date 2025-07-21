package fr.geromeavecung.resttddbddddd.domain.fakes;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.authors.Author;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.authors.Authors;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AuthorsInMemory implements Authors {

    private List<Author> values = new ArrayList<>();

    @Override
    public void save(Author author) {
        values.add(author);
    }

    public List<Author> findAll() {
        return values;
    }
}
