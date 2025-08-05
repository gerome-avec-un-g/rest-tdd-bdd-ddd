package fr.geromeavecung.resttddbddddd.domain.boundedcontexts.books;

import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class BookSearchByIdentifier {

    private final Books books;

    public BookSearchByIdentifier(Books books) {
        this.books = books;
    }

    public Optional<Book> execute(UUID bookIdentifier) {
        return books.findByIdentifier(bookIdentifier);
    }

}
