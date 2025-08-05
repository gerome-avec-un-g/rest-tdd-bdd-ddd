package fr.geromeavecung.resttddbddddd.drivers.rest;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.books.Book;
import fr.geromeavecung.resttddbddddd.domain.usecases.CreateABook;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/books")
public class BooksController {

    private final CreateABook createABook;

    public BooksController(CreateABook createABook) {
        this.createABook = createABook;
    }

    @PostMapping
    public ResponseEntity<BookCreationResponse> createAnAuthor(@RequestBody BookCreationRequest bookCreationRequest) {
        Book book = createABook.execute(bookCreationRequest.convert());
        return ResponseEntity.ok(new BookCreationResponse(book));
    }

}
