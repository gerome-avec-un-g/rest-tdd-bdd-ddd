package fr.geromeavecung.resttddbddddd.domain.usecases;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.books.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FindBooksByAuthor {

    public List<Book> execute(UUID authorIdentifier) {
        return null;
    }

}
