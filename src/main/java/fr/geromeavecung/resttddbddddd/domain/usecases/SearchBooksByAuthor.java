package fr.geromeavecung.resttddbddddd.domain.usecases;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.authors.AuthorSearchByIdentifier;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.books.Book;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.books.BookSearchByAuthor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SearchBooksByAuthor {

    private final AuthorSearchByIdentifier authorSearchByIdentifier;
    private final BookSearchByAuthor bookSearchByAuthor;

    public SearchBooksByAuthor(AuthorSearchByIdentifier authorSearchByIdentifier, BookSearchByAuthor bookSearchByAuthor) {
        this.authorSearchByIdentifier = authorSearchByIdentifier;
        this.bookSearchByAuthor = bookSearchByAuthor;
    }

    public List<Book> execute(UUID authorIdentifier) {
        authorSearchByIdentifier.execute(authorIdentifier);
        return bookSearchByAuthor.execute(authorIdentifier);
    }

}
