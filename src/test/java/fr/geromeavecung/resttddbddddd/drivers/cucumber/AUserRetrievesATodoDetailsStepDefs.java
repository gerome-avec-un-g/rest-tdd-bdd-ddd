package fr.geromeavecung.resttddbddddd.drivers.cucumber;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.todos.TodoDetails;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.todos.TodoIdentifier;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.users.User;
import fr.geromeavecung.resttddbddddd.domain.usecases.todos.RetrieveTodo;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class AUserRetrievesATodoDetailsStepDefs {

    private final RetrieveTodo retrieveTodo;
    private User user;

    private TodoDetails todoDetails;

    @Autowired
    public AUserRetrievesATodoDetailsStepDefs(RetrieveTodo retrieveTodo) {
        this.retrieveTodo = retrieveTodo;
    }

    @Given("the user {string}")
    public void the_user(String string) {
        // TODO Write code here that turns the phrase above into concrete actions
    }
    @When("the user retrieves a todo details for todo {int}")
    public void the_user_retrieves_a_todo_details_for_todo(int todoIdentifier) {
        todoDetails = retrieveTodo.retrieveTodo(new TodoIdentifier(todoIdentifier));
    }
    @Then("the todo details is {string}")
    public void the_todo_details_is(String title) {
        assertThat(todoDetails.title()).isEqualTo(title);
    }

}
