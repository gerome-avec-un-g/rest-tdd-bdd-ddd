package fr.geromeavecung.library.domain.usecases;

import fr.geromeavecung.library.domain.boundedcontexts.authors.AuthorExists;
import fr.geromeavecung.library.domain.boundedcontexts.books.Book;
import fr.geromeavecung.library.domain.boundedcontexts.books.BookSearchByAuthor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SearchBooksByAuthor {

    private final AuthorExists authorExists;
    private final BookSearchByAuthor bookSearchByAuthor;

    public SearchBooksByAuthor(AuthorExists authorExists, BookSearchByAuthor bookSearchByAuthor) {
        this.authorExists = authorExists;
        this.bookSearchByAuthor = bookSearchByAuthor;
    }

    // TODO book with author
    public List<Book> execute(UUID authorIdentifier) {
        authorExists.execute(authorIdentifier);
        return bookSearchByAuthor.execute(authorIdentifier);
    }

}
