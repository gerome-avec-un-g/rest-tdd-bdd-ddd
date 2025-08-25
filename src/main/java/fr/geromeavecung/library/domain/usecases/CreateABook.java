package fr.geromeavecung.library.domain.usecases;

import fr.geromeavecung.library.domain.boundedcontexts.authors.AuthorExists;
import fr.geromeavecung.library.domain.boundedcontexts.books.Book;
import fr.geromeavecung.library.domain.boundedcontexts.books.BookCreation;
import fr.geromeavecung.library.domain.boundedcontexts.shared.DateGenerator;
import org.springframework.stereotype.Service;

import java.time.Year;

@Service
public class CreateABook {

    private final AuthorExists authorExists;
    private final BookCreation bookCreation;
    private final DateGenerator dateGenerator;

    public CreateABook(AuthorExists authorExists, BookCreation bookCreation, DateGenerator dateGenerator) {
        this.authorExists = authorExists;
        this.bookCreation = bookCreation;
        this.dateGenerator = dateGenerator;
    }

    public Book execute(CreateABookCommand createABookCommand) {
        Book book = createABookCommand.convert(Year.of(dateGenerator.now().getYear()));
        authorExists.execute(book.authorIdentifier());
        return bookCreation.save(book);
    }

}
