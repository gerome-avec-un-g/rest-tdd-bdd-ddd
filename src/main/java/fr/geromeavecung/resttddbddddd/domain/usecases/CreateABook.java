package fr.geromeavecung.resttddbddddd.domain.usecases;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.authors.AuthorSearchByIdentifier;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.books.Book;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.books.BookCreation;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.shared.IdentifierGenerator;
import org.springframework.stereotype.Service;

@Service
public class CreateABook {

    private final IdentifierGenerator identifierGenerator;
    private final AuthorSearchByIdentifier authorSearchByIdentifier;
    private final BookCreation bookCreation;

    public CreateABook(IdentifierGenerator identifierGenerator, AuthorSearchByIdentifier authorSearchByIdentifier, BookCreation bookCreation) {
        this.identifierGenerator = identifierGenerator;
        this.authorSearchByIdentifier = authorSearchByIdentifier;
        this.bookCreation = bookCreation;
    }

    public Book execute(CreateABookCommand createABookCommand) {
        Book book = createABookCommand.convert(identifierGenerator.generate());
        authorSearchByIdentifier.execute(book.authorIdentifier());
        return bookCreation.save(book);
    }

}
