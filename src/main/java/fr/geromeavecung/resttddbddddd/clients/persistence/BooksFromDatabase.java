package fr.geromeavecung.resttddbddddd.clients.persistence;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.books.Book;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.books.Books;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class BooksFromDatabase implements Books {

    @Override
    public void save(Book book) {

    }

    @Override
    public List<Book> findAllByAuthor(UUID uuid) {
        return List.of();
    }

}
