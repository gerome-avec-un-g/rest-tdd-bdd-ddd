package fr.geromeavecung.resttddbddddd.clients.rest;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.shared.UserIdentifier;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.users.Email;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.users.Name;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.users.User;

public record UserJson(int id, String name, String email, AddressJson address) {

    public User toDomain() {
        return new User(new UserIdentifier(id), new Name(name),
                new Email(email), address.toDomain());
    }

}
