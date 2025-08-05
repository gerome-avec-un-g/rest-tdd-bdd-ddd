package fr.geromeavecung.resttddbddddd.domain.steps;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.books.Book;
import fr.geromeavecung.resttddbddddd.domain.usecases.SearchABookByIdentifier;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchBookByIdentifierStepDefs {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchBookByIdentifierStepDefs.class);

    private final SearchABookByIdentifier searchABookByIdentifier;
    private final SharedState sharedState;

    private Book book;

    public SearchBookByIdentifierStepDefs(SearchABookByIdentifier searchABookByIdentifier, SharedState sharedState) {
        this.searchABookByIdentifier = searchABookByIdentifier;
        this.sharedState = sharedState;
    }

    @When("I search for the book {word}")
    public void i_search_for_the_book(String bookIdentifier) {
        try {
            book = searchABookByIdentifier.execute(UUID.fromString(bookIdentifier));
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
            sharedState.setException(exception);
        }
    }

    @Then("the book is")
    public void the_book_is(List<Book> expectedBooks) {
        assertThat(book).isEqualTo(expectedBooks.getFirst());
    }

}
