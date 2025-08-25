package fr.geromeavecung.library.drivers.ui;

import fr.geromeavecung.library.domain.usecases.SearchForAuthors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping(value = "/author")
public class AuthorUIController {

    private final SearchForAuthors searchForAuthors;

    public AuthorUIController(SearchForAuthors searchForAuthors) {
        this.searchForAuthors = searchForAuthors;
    }

    @GetMapping("/{authorIdentifier}")
    public String findAuthor(Model model, @PathVariable String authorIdentifier) {
        AuthorDetails authorDetails = new AuthorDetails(searchForAuthors.findByIdentifier(UUID.fromString(authorIdentifier)));
        model.addAttribute("author", authorDetails);
        return "author";
    }

}
