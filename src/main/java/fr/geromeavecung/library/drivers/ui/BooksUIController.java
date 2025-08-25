package fr.geromeavecung.library.drivers.ui;

import fr.geromeavecung.library.domain.boundedcontexts.authors.Author;
import fr.geromeavecung.library.domain.boundedcontexts.authors.SearchForAuthorsCommand;
import fr.geromeavecung.library.domain.boundedcontexts.books.Book;
import fr.geromeavecung.library.domain.boundedcontexts.books.ISBN;
import fr.geromeavecung.library.domain.usecases.SearchABookByIdentifier;
import fr.geromeavecung.library.domain.usecases.SearchBooksByAuthor;
import fr.geromeavecung.library.domain.usecases.SearchForAuthors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "/books")
public class BooksUIController {

    private final SearchABookByIdentifier searchABookByIdentifier;
    private final SearchForAuthors searchForAuthors;

    public BooksUIController(SearchABookByIdentifier searchABookByIdentifier, SearchForAuthors searchForAuthors) {
        this.searchABookByIdentifier = searchABookByIdentifier;
        this.searchForAuthors = searchForAuthors;
    }


//    @GetMapping
//    public String allAuthors(Model model) {
//        model.addAttribute("authorSearch", new SearchForAuthorsCommandDTO());
//        return "authors";
//    }

    @GetMapping("/{isbn}")
    public String findBook(Model model, @PathVariable String isbn) {
        // TODO move to a back for front use case
        Book book = searchABookByIdentifier.execute(new ISBN(isbn));
        Author author = searchForAuthors.findByIdentifier(book.authorIdentifier());
        model.addAttribute("book", new BookSummary(book, author));
        return "book";
    }

//    @PostMapping
//    public String searchForAuthors(@ModelAttribute SearchForAuthorsCommandDTO searchForAuthorsCommandDTO, Model model) {
//        List<AuthorSummary> authorSummaries = searchForAuthors.execute(new SearchForAuthorsCommand(searchForAuthorsCommandDTO.getSearchTerm())).stream()
//                .map(AuthorSummary::new)
//                .toList();
//        //authorSummaries.forEach(authorSummaries -> authorSummaries.add(linkTo(methodOn(AuthorUIController.class).author(authorSummaries.getIdentifier())).withSelfRel()));
//        model.addAttribute("authors", authorSummaries);
//        model.addAttribute("authorSearch", new SearchForAuthorsCommandDTO());
//        return "authors";
//    }
//
//    @GetMapping("/{authorIdentifier}/books")
//    public String booksByAuthor(Model model, @PathVariable String authorIdentifier) {
//        List<BookSummary> bookSummaries = searchBooksByAuthor.execute(UUID.fromString(authorIdentifier)).stream()
//                .map(BookSummary::new)
//                .toList();
//        model.addAttribute("books", bookSummaries);
//        return "author-books";
//    }

}
