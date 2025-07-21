package fr.geromeavecung.resttddbddddd.domain.usecases.todos;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.authors.Author;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.authors.Authors;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadAuthors {

    private final Authors authors;

    public ReadAuthors(Authors authors) {
        this.authors = authors;
    }

    public List<Author> readAll() {
        return authors.findAll();
    }
}
