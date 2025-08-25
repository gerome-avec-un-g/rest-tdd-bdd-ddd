package fr.geromeavecung.library.drivers.ui;

import fr.geromeavecung.library.domain.usecases.SearchForAuthors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/authors")
public class AuthorsUIController {

    private final SearchForAuthors searchForAuthors;

    public AuthorsUIController(SearchForAuthors searchForAuthors) {
        this.searchForAuthors = searchForAuthors;
    }

    @GetMapping
    public String allAuthors(Model model) {
        List<AuthorSummary> authorSummaries = searchForAuthors.findAll().stream()
                .map(AuthorSummary::new)
                .toList();
        //authorSummaries.forEach(authorSummaries -> authorSummaries.add(linkTo(methodOn(AuthorUIController.class).author(authorSummaries.getIdentifier())).withSelfRel()));
        model.addAttribute("authors", authorSummaries);
        return "authors";
    }

}
