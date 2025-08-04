package fr.geromeavecung.resttddbddddd.clients.persistence;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.books.Book;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.books.Books;
import org.springframework.stereotype.Repository;

@Repository
public class BooksFromDatabase implements Books {

    @Override
    public void save(Book book) {

    }

}
