package fr.geromeavecung.resttddbddddd.drivers.cucumber.steps;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.authors.Author;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.authors.AuthorCreationCommand;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.authors.Authors;
import fr.geromeavecung.resttddbddddd.domain.usecases.todos.CreateAnAuthor;
import fr.geromeavecung.resttddbddddd.drivers.cucumber.fakes.AuthorsInMemory;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateAnAuthorStepDefs {

    private final CreateAnAuthor createAnAuthor;

    private final AuthorsInMemory authors;
    private Exception exception;
    private Author author;

    public CreateAnAuthorStepDefs(CreateAnAuthor createAnAuthor, AuthorsInMemory authors) {
        this.createAnAuthor = createAnAuthor;
        this.authors = authors;
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

    @When("I create an author with first name {word}")
    public void i_create_an_author_named_firstname_doe(String firstName) {
        try {
            author = createAnAuthor.execute(new AuthorCreationCommand(stringType(firstName), "Doe"));
        } catch (Exception e) {
            exception = e;
        }
    }

    @When("I create an author with last name {word}")
    public void i_create_an_author_named_john_lastname(String lastName) {
        try {
            author = createAnAuthor.execute(new AuthorCreationCommand("John", stringType(lastName)));
        } catch (Exception e) {
            exception = e;
        }
    }

    @When("I create an author named {word} {word}")
    public void i_create_an_author_named_firstName_lastname(String firstName, String lastName) {
        try {
            author = createAnAuthor.execute(new AuthorCreationCommand(firstName, stringType(lastName)));
        } catch (Exception e) {
            exception = e;
        }
    }

    @Then("an error is raised with message {string}")
    public void an_error_is_raised_with_message(String message) {
        assertThat(exception).hasMessage(message);
    }

    @Then("the author {word} {word} is created with its unique identifier {string}")
    public void the_author_firstName_lastname_is_created_with_its_unique_identifier(String firstName, String lastName, String uniqueIdentifier) {
        //verify(authors).save(author);
        // TODO mock/H2
        assertThat(authors.findAll()).contains(author);
        assertThat(author).isEqualTo(new Author(UUID.fromString(uniqueIdentifier), firstName, lastName));
    }
}
