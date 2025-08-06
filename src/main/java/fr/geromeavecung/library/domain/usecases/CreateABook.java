package fr.geromeavecung.library.domain.usecases;

import fr.geromeavecung.library.domain.boundedcontexts.authors.AuthorSearchByIdentifier;
import fr.geromeavecung.library.domain.boundedcontexts.books.Book;
import fr.geromeavecung.library.domain.boundedcontexts.books.BookCreation;
import org.springframework.stereotype.Service;

@Service
public class CreateABook {

    private final AuthorSearchByIdentifier authorSearchByIdentifier;
    private final BookCreation bookCreation;

    public CreateABook(AuthorSearchByIdentifier authorSearchByIdentifier, BookCreation bookCreation) {
        this.authorSearchByIdentifier = authorSearchByIdentifier;
        this.bookCreation = bookCreation;
    }

    public Book execute(CreateABookCommand createABookCommand) {
        Book book = createABookCommand.convert();
        authorSearchByIdentifier.execute(book.authorIdentifier());
        return bookCreation.save(book);
    }

}
