package fr.geromeavecung.library.drivers.ui;

import fr.geromeavecung.library.drivers.rest.AuthorCreationRequest;
import fr.geromeavecung.library.drivers.rest.AuthorCreationResponse;
import fr.geromeavecung.library.drivers.rest.AuthorsSearchResponse;
import fr.geromeavecung.library.drivers.rest.FindBooksByAuthorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/authors")
public class AuthorsUIController {

    @GetMapping
    public ResponseEntity<AuthorsSearchResponse> searchForAuthors(@RequestParam String searchTerm) {
        return null;
    }

    @GetMapping("/{authorIdentifier}/books")
    public ResponseEntity<FindBooksByAuthorResponse> findBooksByAuthor(@PathVariable String authorIdentifier) {
        return null;
    }

    @PostMapping
    public ResponseEntity<AuthorCreationResponse> createAnAuthor(@RequestBody AuthorCreationRequest authorCreationRequest) {
        return null;
    }

}
