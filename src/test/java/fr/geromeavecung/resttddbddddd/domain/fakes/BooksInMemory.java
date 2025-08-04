package fr.geromeavecung.resttddbddddd.domain.fakes;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.books.Book;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.books.Books;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class BooksInMemory implements Books {

    List<Book> books = new ArrayList<>();

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

    public List<Book> findAll() {
        return books;
    }
}
