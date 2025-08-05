package fr.geromeavecung.resttddbddddd.domain.usecases;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.books.Book;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SearchABookByIdentifier {

    public Book execute(UUID bookIdentifier) {
        return null;
    }

}
