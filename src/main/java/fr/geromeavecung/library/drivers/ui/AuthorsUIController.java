package fr.geromeavecung.library.drivers.ui;

import fr.geromeavecung.library.domain.boundedcontexts.authors.SearchForAuthorsCommand;
import fr.geromeavecung.library.domain.usecases.SearchForAuthors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
        model.addAttribute("authorSearch", new SearchForAuthorsCommandDTO());
        return "authors";
    }

    @PostMapping
    public String searchForAuthors(@ModelAttribute SearchForAuthorsCommandDTO searchForAuthorsCommandDTO, Model model) {
        List<AuthorSummary> authorSummaries = searchForAuthors.execute(new SearchForAuthorsCommand(searchForAuthorsCommandDTO.getSearchTerm())).stream()
                .map(AuthorSummary::new)
                .toList();
        //authorSummaries.forEach(authorSummaries -> authorSummaries.add(linkTo(methodOn(AuthorUIController.class).author(authorSummaries.getIdentifier())).withSelfRel()));
        model.addAttribute("authors", authorSummaries);
        model.addAttribute("authorSearch", new SearchForAuthorsCommandDTO());
        return "authors";
    }

}
