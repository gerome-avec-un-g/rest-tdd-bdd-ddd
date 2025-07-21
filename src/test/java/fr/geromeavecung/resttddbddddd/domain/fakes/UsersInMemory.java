package fr.geromeavecung.resttddbddddd.domain.fakes;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.shared.UserIdentifier;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.users.User;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.users.Users;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsersInMemory implements Users {

    private final List<User> values;

    public UsersInMemory() {
        this.values = null;
    }

    @Override
    public User retrieveUser(UserIdentifier userIdentifier) {
        return null;
    }
}
