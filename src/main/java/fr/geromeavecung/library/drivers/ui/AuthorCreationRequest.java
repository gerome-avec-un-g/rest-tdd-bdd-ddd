package fr.geromeavecung.library.drivers.ui;

import fr.geromeavecung.library.domain.usecases.CreateAnAuthorCommand;

public class AuthorCreationRequest {

    private String firstName;
    private String lastName;

    CreateAnAuthorCommand convert() {
        return new CreateAnAuthorCommand(firstName, lastName);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
