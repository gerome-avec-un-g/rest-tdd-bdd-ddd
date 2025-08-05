package fr.geromeavecung.resttddbddddd.domain.steps;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.books.Book;
import fr.geromeavecung.resttddbddddd.domain.usecases.SearchBooksByAuthor;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchBooksByAuthorStepDefs {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchBooksByAuthorStepDefs.class);

    private final SearchBooksByAuthor searchBooksByAuthor;
    private final SharedState sharedState;

    private List<Book> books;

    public SearchBooksByAuthorStepDefs(SearchBooksByAuthor searchBooksByAuthor, SharedState sharedState) {
        this.searchBooksByAuthor = searchBooksByAuthor;
        this.sharedState = sharedState;
    }

    @When("I search for the books of {word}")
    public void i_search_for_the_books_of_author(String authorIdentifier) {
        try {
            books = searchBooksByAuthor.execute(UUID.fromString(authorIdentifier));
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
            sharedState.setException(exception);
        }
    }

    @Then("the books are")
    public void the_books_are(List<Book> expectedBooks) {
        assertThat(books).isEqualTo(expectedBooks);
    }

}
