package fr.geromeavecung.resttddbddddd.drivers.rest;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.authors.Author;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.authors.SearchForAuthorsCommand;
import fr.geromeavecung.resttddbddddd.domain.usecases.CreateAnAuthor;
import fr.geromeavecung.resttddbddddd.domain.usecases.SearchForAuthors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/authors")
public class AuthorController {

    private final CreateAnAuthor createAnAuthor;
    private final SearchForAuthors searchForAuthors;

    public AuthorController(CreateAnAuthor createAnAuthor, SearchForAuthors searchForAuthors) {
        this.createAnAuthor = createAnAuthor;
        this.searchForAuthors = searchForAuthors;
    }

    @GetMapping
    public ResponseEntity<AuthorsSearchResponse> searchForAuthors(@RequestParam String searchTerm) {
        List<Author> authors = searchForAuthors.execute(new SearchForAuthorsCommand(searchTerm));
        return ResponseEntity.ok(new AuthorsSearchResponse(authors));
    }

    @PostMapping
    public ResponseEntity<AuthorCreationResponse> createAnAuthor(@RequestBody AuthorCreationRequest authorCreationRequest) {
        Author author = createAnAuthor.execute(authorCreationRequest.convert());
        return ResponseEntity.ok(new AuthorCreationResponse(author));
    }

}
