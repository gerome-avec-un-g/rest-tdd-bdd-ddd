package fr.geromeavecung.library.drivers.ui;

import fr.geromeavecung.library.domain.usecases.CreateAnAuthor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/author-creation")
public class AuthorCreationUIController {

    private final CreateAnAuthor createAnAuthor;

    public AuthorCreationUIController(CreateAnAuthor createAnAuthor) {
        this.createAnAuthor = createAnAuthor;
    }

    @GetMapping()
    public String findAuthor(Model model) {
        model.addAttribute("newAuthor", new AuthorCreationRequest());
        return "author-creation";
    }

    @PostMapping
    public String createAnAuthor(@ModelAttribute AuthorCreationRequest authorCreationRequest, Model model) {
        createAnAuthor.execute(authorCreationRequest.convert());
        return "redirect:/authors";
    }

}
