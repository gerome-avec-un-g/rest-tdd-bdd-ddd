package fr.geromeavecung.library.domain.usecases;

import fr.geromeavecung.library.domain.boundedcontexts.books.Book;
import fr.geromeavecung.library.domain.boundedcontexts.books.BookSearchByIdentifier;
import fr.geromeavecung.library.domain.boundedcontexts.books.ISBN;
import fr.geromeavecung.library.domain.boundedcontexts.shared.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SearchABookByIdentifier {

    private final BookSearchByIdentifier bookSearchByIdentifier;

    public SearchABookByIdentifier(BookSearchByIdentifier bookSearchByIdentifier) {
        this.bookSearchByIdentifier = bookSearchByIdentifier;
    }

    public Book execute(ISBN isbn) {
        return bookSearchByIdentifier.execute(isbn).orElseThrow(() -> new NotFoundException("Book %s not found".formatted(isbn)));
    }

}
