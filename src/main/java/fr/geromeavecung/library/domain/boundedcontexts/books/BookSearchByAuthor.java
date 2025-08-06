package fr.geromeavecung.library.domain.boundedcontexts.books;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookSearchByAuthor {

    private final Books books;

    public BookSearchByAuthor(Books books) {
        this.books = books;
    }

    public List<Book> execute(UUID authorIdentifier) {
        return books.findAllByAuthor(authorIdentifier);
    }

}
