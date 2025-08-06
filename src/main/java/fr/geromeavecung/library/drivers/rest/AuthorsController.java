package fr.geromeavecung.library.drivers.rest;

import fr.geromeavecung.library.domain.boundedcontexts.authors.Author;
import fr.geromeavecung.library.domain.boundedcontexts.authors.SearchForAuthorsCommand;
import fr.geromeavecung.library.domain.boundedcontexts.books.Book;
import fr.geromeavecung.library.domain.usecases.CreateAnAuthor;
import fr.geromeavecung.library.domain.usecases.SearchBooksByAuthor;
import fr.geromeavecung.library.domain.usecases.SearchForAuthors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/authors")
public class AuthorsController {

    private final CreateAnAuthor createAnAuthor;
    private final SearchForAuthors searchForAuthors;
    private final SearchBooksByAuthor searchBooksByAuthor;

    public AuthorsController(CreateAnAuthor createAnAuthor, SearchForAuthors searchForAuthors, SearchBooksByAuthor searchBooksByAuthor) {
        this.createAnAuthor = createAnAuthor;
        this.searchForAuthors = searchForAuthors;
        this.searchBooksByAuthor = searchBooksByAuthor;
    }

    @GetMapping
    public ResponseEntity<AuthorsSearchResponse> searchForAuthors(@RequestParam String searchTerm) {
        List<Author> authors = searchForAuthors.execute(new SearchForAuthorsCommand(searchTerm));
        return ResponseEntity.ok(new AuthorsSearchResponse(authors));
    }

    @GetMapping("/{authorIdentifier}/books")
    public ResponseEntity<FindBooksByAuthorResponse> findBooksByAuthor(@PathVariable String authorIdentifier) {
        List<Book> books = searchBooksByAuthor.execute(UUID.fromString(authorIdentifier));
        List<BookSummaryResponse> bookCreationResponses = books.stream()
                .map(BookSummaryResponse::create)
                .toList();
        bookCreationResponses.forEach(bookCreationResponse -> bookCreationResponse.add(linkTo(methodOn(BooksController.class).searchForBook(bookCreationResponse.getIsbn())).withSelfRel()));
        return ResponseEntity.ok(new FindBooksByAuthorResponse(bookCreationResponses));
    }

    @PostMapping
    public ResponseEntity<AuthorCreationResponse> createAnAuthor(@RequestBody AuthorCreationRequest authorCreationRequest) {
        Author author = createAnAuthor.execute(authorCreationRequest.convert());
        return ResponseEntity.ok(new AuthorCreationResponse(author));
    }

}
