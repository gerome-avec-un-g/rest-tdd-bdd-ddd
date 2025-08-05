package fr.geromeavecung.resttddbddddd.drivers.rest;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.books.Book;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.books.ISBN;
import fr.geromeavecung.resttddbddddd.domain.usecases.CreateABook;
import fr.geromeavecung.resttddbddddd.domain.usecases.SearchABookByIdentifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/books")
public class BooksController {

    private final CreateABook createABook;
    private final SearchABookByIdentifier searchABookByIdentifier;

    public BooksController(CreateABook createABook, SearchABookByIdentifier searchABookByIdentifier) {
        this.createABook = createABook;
        this.searchABookByIdentifier = searchABookByIdentifier;
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<BookCreationResponse> searchForBook(@PathVariable String isbn) {
        Book book = searchABookByIdentifier.execute(new ISBN(isbn));
        return ResponseEntity.ok(BookCreationResponse.create(book));
    }

    @PostMapping
    public ResponseEntity<BookCreationResponse> createABook(@RequestBody BookCreationRequest bookCreationRequest) {
        Book book = createABook.execute(bookCreationRequest.convert());
        return ResponseEntity.ok(BookCreationResponse.create(book));
    }

}
