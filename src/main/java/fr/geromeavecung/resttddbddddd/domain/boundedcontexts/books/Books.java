package fr.geromeavecung.resttddbddddd.domain.boundedcontexts.books;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface Books {

    void save(Book book);
    List<Book> findAllByAuthor(UUID authorIdentifier);
    Optional<Book> findByIdentifier(ISBN isbn);

}
