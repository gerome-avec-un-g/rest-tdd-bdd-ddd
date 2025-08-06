package fr.geromeavecung.library.domain.boundedcontexts.books;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookSearchByIdentifier {

    private final Books books;

    public BookSearchByIdentifier(Books books) {
        this.books = books;
    }

    public Optional<Book> execute(ISBN isbn) {
        return books.findByIdentifier(isbn);
    }

}
