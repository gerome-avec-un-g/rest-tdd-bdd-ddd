package fr.geromeavecung.resttddbddddd.domain.boundedcontexts.books;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.shared.BusinessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookCreation {

    private final Books books;

    public BookCreation(Books books) {
        this.books = books;
    }

    public Book save(Book book) {
        List<Book> booksBySameAuthor = books.findAllByAuthor(book.authorIdentifier());
        if (booksBySameAuthor.stream().anyMatch(existingBook -> existingBook.title().value().equalsIgnoreCase(book.title().value()))) {
            throw new BusinessException("Book '%s' already exists for '%s'".formatted(book.title().value(), book.authorIdentifier()));
        }
        books.save(book);
        return book;
    }

}
