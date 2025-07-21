package fr.geromeavecung.resttddbddddd.drivers.rest;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.authors.Author;
import fr.geromeavecung.resttddbddddd.domain.usecases.CreateAnAuthor;
import fr.geromeavecung.resttddbddddd.domain.usecases.ReadAuthors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/authors")
public class AuthorController {

    private final CreateAnAuthor createAnAuthor;
    private final ReadAuthors readAuthors;

    public AuthorController(CreateAnAuthor createAnAuthor, ReadAuthors readAuthors) {
        this.createAnAuthor = createAnAuthor;
        this.readAuthors = readAuthors;
    }

    @GetMapping
    public ResponseEntity<List<Author>> getAuthors() {
        return ResponseEntity.ok(readAuthors.readAll());
    }

    @PostMapping
    public ResponseEntity<AuthorCreationResponse> getTodo(@RequestBody AuthorCreationRequest authorCreationRequest) {
        Author author = createAnAuthor.execute(authorCreationRequest.convert());
        return ResponseEntity.ok(new AuthorCreationResponse(author));
    }

}
