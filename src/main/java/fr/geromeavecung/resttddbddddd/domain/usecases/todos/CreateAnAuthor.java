package fr.geromeavecung.resttddbddddd.domain.usecases.todos;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.authors.Author;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.authors.AuthorCreationCommand;
import org.springframework.stereotype.Service;

@Service
public class CreateAnAuthor {

    public Author execute(AuthorCreationCommand authorCreationCommand) {
        return null;
    }

}
