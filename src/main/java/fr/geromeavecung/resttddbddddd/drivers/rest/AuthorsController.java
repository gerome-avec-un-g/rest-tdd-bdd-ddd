package fr.geromeavecung.resttddbddddd.drivers.rest;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.authors.Author;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.authors.SearchForAuthorsCommand;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.books.Book;
import fr.geromeavecung.resttddbddddd.domain.usecases.CreateAnAuthor;
import fr.geromeavecung.resttddbddddd.domain.usecases.FindBooksByAuthor;
import fr.geromeavecung.resttddbddddd.domain.usecases.SearchForAuthors;
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

@RestController
@RequestMapping(value = "/authors")
public class AuthorsController {

    private final CreateAnAuthor createAnAuthor;
    private final SearchForAuthors searchForAuthors;
    private final FindBooksByAuthor findBooksByAuthor;

    public AuthorsController(CreateAnAuthor createAnAuthor, SearchForAuthors searchForAuthors, FindBooksByAuthor findBooksByAuthor) {
        this.createAnAuthor = createAnAuthor;
        this.searchForAuthors = searchForAuthors;
        this.findBooksByAuthor = findBooksByAuthor;
    }

    @GetMapping
    public ResponseEntity<AuthorsSearchResponse> searchForAuthors(@RequestParam String searchTerm) {
        List<Author> authors = searchForAuthors.execute(new SearchForAuthorsCommand(searchTerm));
        return ResponseEntity.ok(new AuthorsSearchResponse(authors));
    }

    @GetMapping("/{authorIdentifier}/books")
    public ResponseEntity<FindBooksByAuthorResponse> findBooksByAuthor(@PathVariable String authorIdentifier) {
        List<Book> books = findBooksByAuthor.execute(UUID.fromString(authorIdentifier));
        return ResponseEntity.ok(new FindBooksByAuthorResponse(books.stream().map(BookCreationResponse::new).toList()));
    }

    @PostMapping
    public ResponseEntity<AuthorCreationResponse> createAnAuthor(@RequestBody AuthorCreationRequest authorCreationRequest) {
        Author author = createAnAuthor.execute(authorCreationRequest.convert());
        return ResponseEntity.ok(new AuthorCreationResponse(author));
    }

}
