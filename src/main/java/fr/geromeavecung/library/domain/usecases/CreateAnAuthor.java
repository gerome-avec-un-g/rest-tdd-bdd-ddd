package fr.geromeavecung.library.domain.usecases;

import fr.geromeavecung.library.domain.boundedcontexts.authors.Author;
import fr.geromeavecung.library.domain.boundedcontexts.authors.AuthorCreation;
import fr.geromeavecung.library.domain.boundedcontexts.shared.IdentifierGenerator;
import org.springframework.stereotype.Service;

@Service
public class CreateAnAuthor {

    private final AuthorCreation authorCreation;
    private final IdentifierGenerator identifierGenerator;

    public CreateAnAuthor(AuthorCreation authorCreation, IdentifierGenerator identifierGenerator) {
        this.authorCreation = authorCreation;
        this.identifierGenerator = identifierGenerator;
    }

    public Author execute(CreateAnAuthorCommand createAnAuthorCommand) {
        Author author = createAnAuthorCommand.convert(identifierGenerator.generate());
        authorCreation.save(author);
        return author;
    }

}
