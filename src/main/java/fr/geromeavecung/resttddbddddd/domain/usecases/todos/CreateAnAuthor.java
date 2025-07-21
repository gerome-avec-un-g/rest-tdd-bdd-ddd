package fr.geromeavecung.resttddbddddd.domain.usecases.todos;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.authors.Author;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.authors.AuthorCreation;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.authors.AuthorCreationCommand;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.shared.IdentifierGenerator;
import org.springframework.stereotype.Service;

@Service
public class CreateAnAuthor {

    private final AuthorCreation authorCreation;
    private final IdentifierGenerator identifierGenerator;

    public CreateAnAuthor(AuthorCreation authorCreation, IdentifierGenerator identifierGenerator) {
        this.authorCreation = authorCreation;
        this.identifierGenerator = identifierGenerator;
    }

    public Author execute(AuthorCreationCommand authorCreationCommand) {
        Author author = authorCreationCommand.convert(identifierGenerator.generate());
        authorCreation.save(author);
        return author;
    }

}
