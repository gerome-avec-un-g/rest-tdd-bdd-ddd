package fr.geromeavecung.resttddbddddd.domain.steps;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.authors.Author;
import fr.geromeavecung.resttddbddddd.domain.usecases.SearchForAuthorsCommand;
import fr.geromeavecung.resttddbddddd.domain.fakes.AuthorsInMemory;
import fr.geromeavecung.resttddbddddd.domain.usecases.SearchForAuthors;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchForAuthorsStepDefs {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchForAuthorsStepDefs.class);

    private final SearchForAuthors searchForAuthors;
    private final AuthorsInMemory authorsInMemory;
    private final SharedState sharedState;

    private List<Author> authors;

    public SearchForAuthorsStepDefs(SearchForAuthors searchForAuthors, AuthorsInMemory authorsInMemory, SharedState sharedState) {
        this.searchForAuthors = searchForAuthors;
        this.authorsInMemory = authorsInMemory;
        this.sharedState = sharedState;
    }

    @DataTableType
    public Author author(Map<String, String> row) {
        return new Author(UUID.fromString(row.get("author identifier")), row.get("first name"), row.get("last name"));
    }

    @Given("The following authors in the system")
    public void the_following_authors_in_the_system(List<Author> authors) {
        authors.forEach(authorsInMemory::save);
    }

    @When("I search for {string}")
    public void i_search_for_search_term(String searchTerm) {
        try {
            authors = searchForAuthors.execute(new SearchForAuthorsCommand(SharedStepDefs.sanitize(searchTerm)));
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
            sharedState.setException(exception);
        }
    }

    @Then("the result contains {string}")
    public void the_result_contains_result(String results) {
        String actualResult = authors.stream()
                .map(author -> author.firstName() + " " + author.lastName())
                .collect(Collectors.joining(", "));
        assertThat(actualResult).isEqualTo(results);
    }

}
