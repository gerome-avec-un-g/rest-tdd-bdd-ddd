package fr.geromeavecung.resttddbddddd.drivers.cucumber.steps;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.authors.AuthorCreationCommand;
import fr.geromeavecung.resttddbddddd.domain.usecases.todos.CreateAnAuthor;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateAnAuthorStepDefs {

    private final CreateAnAuthor createAnAuthor;
    private Exception exception;

    public CreateAnAuthorStepDefs(CreateAnAuthor createAnAuthor) {
        this.createAnAuthor = createAnAuthor;
    }

    @When("I create an author named <first name> Doe")
    public void i_create_an_author_named_firstname_doe(String firstName) {
        try {
            createAnAuthor.execute(new AuthorCreationCommand(firstName, "Doe"));
        } catch (Exception e) {
            exception = e;
        }
    }

    @Then("an error is raised with message {string}")
    public void an_error_is_raised_with_message(String message) {
        assertThat(exception).hasMessage(message);
    }

}
