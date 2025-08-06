package fr.geromeavecung.library.domain.steps;

import fr.geromeavecung.library.domain.boundedcontexts.authors.Author;
import fr.geromeavecung.library.domain.usecases.CreateAnAuthorCommand;
import fr.geromeavecung.library.domain.fakes.AuthorsInMemory;
import fr.geromeavecung.library.domain.usecases.CreateAnAuthor;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateAnAuthorStepDefs {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateAnAuthorStepDefs.class);

    private final CreateAnAuthor createAnAuthor;
    private final AuthorsInMemory authors;
    private final SharedState sharedState;

    private Author author;

    public CreateAnAuthorStepDefs(CreateAnAuthor createAnAuthor, AuthorsInMemory authors, SharedState sharedState) {
        this.createAnAuthor = createAnAuthor;
        this.authors = authors;
        this.sharedState = sharedState;
    }

    @Given("The author {word} {word} exists in the system")
    public void the_author_firstName_lastname_exists_in_the_system(String firstName, String lastName) {
        authors.save(new Author(UUID.randomUUID(), firstName, lastName));
    }

    @When("I create an author with first name {word}")
    public void i_create_an_author_named_firstname_doe(String firstName) {
        createAnAuthor(firstName, "Doe");
    }

    @When("I create an author with last name {word}")
    public void i_create_an_author_named_john_lastname(String lastName) {
        createAnAuthor("John", lastName);
    }

    @When("I create an author named {word} {word}")
    public void i_create_an_author_named_firstName_lastname(String firstName, String lastName) {
        createAnAuthor(firstName, lastName);
    }

    private void createAnAuthor(String firstName, String lastName) {
        try {
            author = createAnAuthor.execute(new CreateAnAuthorCommand(SharedStepDefs.sanitize(firstName), SharedStepDefs.sanitize(lastName)));
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
            sharedState.setException(exception);
        }
    }

    @Then("the author {word} {word} is created with its unique identifier {string}")
    public void the_author_firstName_lastname_is_created_with_its_unique_identifier(String firstName, String lastName, String uniqueIdentifier) {
        // TODO mock/H2
        assertThat(authors.findAll()).contains(author);
        assertThat(author).isEqualTo(new Author(UUID.fromString(uniqueIdentifier), firstName, lastName));
    }

}
