package fr.geromeavecung.resttddbddddd.domain.usecases;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.books.Book;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.books.BookSearchByIdentifier;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.shared.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SearchABookByIdentifier {

    private final BookSearchByIdentifier bookSearchByIdentifier;

    public SearchABookByIdentifier(BookSearchByIdentifier bookSearchByIdentifier) {
        this.bookSearchByIdentifier = bookSearchByIdentifier;
    }

    public Book execute(UUID bookIdentifier) {
        return bookSearchByIdentifier.execute(bookIdentifier).orElseThrow(() -> new NotFoundException("Book %s not found".formatted(bookIdentifier)));
    }

}
