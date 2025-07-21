package fr.geromeavecung.resttddbddddd.drivers.rest;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.authors.Author;
import fr.geromeavecung.resttddbddddd.domain.usecases.todos.CreateAnAuthor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/authors")
public class AuthorController {

    private final CreateAnAuthor createAnAuthor;

    public AuthorController(CreateAnAuthor createAnAuthor) {
        this.createAnAuthor = createAnAuthor;
    }

    @PostMapping
    public ResponseEntity<AuthorCreationResponse> getTodo(@RequestBody AuthorCreationRequest authorCreationRequest) {
        Author author = createAnAuthor.execute(authorCreationRequest.convert());
        return ResponseEntity.ok(new AuthorCreationResponse(author));
    }

}
