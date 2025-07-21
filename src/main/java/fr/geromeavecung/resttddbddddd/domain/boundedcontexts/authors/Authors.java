package fr.geromeavecung.resttddbddddd.domain.boundedcontexts.authors;

import java.util.List;

public interface Authors {

    void save(Author author);

    List<Author> findAll();

}
