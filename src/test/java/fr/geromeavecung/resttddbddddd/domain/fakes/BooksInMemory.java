package fr.geromeavecung.resttddbddddd.domain.fakes;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.books.Book;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.books.Books;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class BooksInMemory implements Books {

    private final List<Book> books = new ArrayList<>();

    @Override
    public void save(Book book) {
        books.add(book);
    }

    @Override
    public List<Book> findAllByAuthor(UUID authorIdentifier) {
        return books.stream()
                .filter(book -> book.authorIdentifier().equals(authorIdentifier))
                .toList();
    }

    @Override
    public Optional<Book> findByIdentifier(UUID bookIdentifier) {
        return Optional.empty();
    }

    public List<Book> findAll() {
        return books;
    }
}
