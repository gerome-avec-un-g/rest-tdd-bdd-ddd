package fr.geromeavecung.library.domain.steps;

import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Then;

import static org.assertj.core.api.Assertions.assertThat;

public class SharedStepDefs {

    private final SharedState sharedState;

    public SharedStepDefs(SharedState sharedState) {
        this.sharedState = sharedState;
    }

    @DataTableType(replaceWithEmptyString = "[empty]")
    public String stringType(String cell) {
        return sanitize(cell);
    }

    // TODO is there a better way?
    public static String sanitize(String cell) {
        if (cell == null || "[null]".equals(cell)) {
            return null;
        }
        if ("[empty]".equals(cell)) {
            return "";
        }
        if ("[blank]".equals(cell)) {
            return " ";
        }
        return cell.replaceAll("'","");
    }

    @Then("an error is raised with message {string}")
    public void an_error_is_raised_with_message(String message) {
        assertThat(sharedState.getException()).hasMessage(message);
    }

}
