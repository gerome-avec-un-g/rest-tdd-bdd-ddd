package fr.geromeavecung.resttddbddddd.drivers.cucumber.steps;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.authors.Author;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.authors.AuthorCreationCommand;
import fr.geromeavecung.resttddbddddd.domain.usecases.todos.CreateAnAuthor;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateAnAuthorStepDefs {

    private final CreateAnAuthor createAnAuthor;
    private Exception exception;
    private Author author;

    public CreateAnAuthorStepDefs(CreateAnAuthor createAnAuthor) {
        this.createAnAuthor = createAnAuthor;
    }

    @DataTableType(replaceWithEmptyString = "[empty]")
    public String stringType(String cell) {
        if (cell == null || "[null]".equals(cell)) {
            return null;
        }
        if ("[empty]".equals(cell)) {
            return "";
        }
        if ("[blank]".equals(cell)) {
            return " ";
        }
        return cell;
    }

    @When("I create an author named {word} Doe")
    public void i_create_an_author_named_firstname_doe(String firstName) {
        try {
            author = createAnAuthor.execute(new AuthorCreationCommand(stringType(firstName), "Doe"));
        } catch (Exception e) {
            exception = e;
        }
    }

    @When("I create an author named John {word}")
    public void i_create_an_author_named_john_lastname(String lastName) {
        try {
            author = createAnAuthor.execute(new AuthorCreationCommand("John", stringType(lastName)));
        } catch (Exception e) {
            exception = e;
        }
    }

    @Then("an error is raised with message {string}")
    public void an_error_is_raised_with_message(String message) {
        assertThat(exception).hasMessage(message);
    }

}
