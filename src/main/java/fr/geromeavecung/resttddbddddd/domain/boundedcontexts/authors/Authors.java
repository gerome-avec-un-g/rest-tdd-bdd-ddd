package fr.geromeavecung.resttddbddddd.domain.boundedcontexts.authors;

import java.util.List;
import java.util.Optional;

public interface Authors {

    void save(Author author);

    List<Author> findAll();

    Optional<Author> find(Author author);
}
