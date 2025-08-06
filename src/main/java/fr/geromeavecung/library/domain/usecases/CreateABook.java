package fr.geromeavecung.library.domain.usecases;

import fr.geromeavecung.library.domain.boundedcontexts.authors.AuthorExists;
import fr.geromeavecung.library.domain.boundedcontexts.books.Book;
import fr.geromeavecung.library.domain.boundedcontexts.books.BookCreation;
import org.springframework.stereotype.Service;

@Service
public class CreateABook {

    private final AuthorExists authorExists;
    private final BookCreation bookCreation;

    public CreateABook(AuthorExists authorExists, BookCreation bookCreation) {
        this.authorExists = authorExists;
        this.bookCreation = bookCreation;
    }

    public Book execute(CreateABookCommand createABookCommand) {
        Book book = createABookCommand.convert();
        authorExists.execute(book.authorIdentifier());
        return bookCreation.save(book);
    }

}
