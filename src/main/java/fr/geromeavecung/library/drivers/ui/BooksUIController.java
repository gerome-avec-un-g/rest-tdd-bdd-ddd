package fr.geromeavecung.library.drivers.ui;

import fr.geromeavecung.library.domain.boundedcontexts.authors.Author;
import fr.geromeavecung.library.domain.boundedcontexts.books.Book;
import fr.geromeavecung.library.domain.boundedcontexts.books.ISBN;
import fr.geromeavecung.library.domain.usecases.SearchABookByIdentifier;
import fr.geromeavecung.library.domain.usecases.SearchForAuthors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/books")
public class BooksUIController {

    private final SearchABookByIdentifier searchABookByIdentifier;
    private final SearchForAuthors searchForAuthors;

    public BooksUIController(SearchABookByIdentifier searchABookByIdentifier, SearchForAuthors searchForAuthors) {
        this.searchABookByIdentifier = searchABookByIdentifier;
        this.searchForAuthors = searchForAuthors;
    }


    @GetMapping("/{isbn}")
    public String findBook(Model model, @PathVariable String isbn) {
        // TODO move to a back for front use case
        Book book = searchABookByIdentifier.execute(new ISBN(isbn));
        Author author = searchForAuthors.findByIdentifier(book.authorIdentifier());
        model.addAttribute("book", new BookSummary(book, author));
        return "book";
    }

}
