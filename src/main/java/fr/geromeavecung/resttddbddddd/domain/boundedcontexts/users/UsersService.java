package fr.geromeavecung.resttddbddddd.domain.boundedcontexts.users;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.shared.UserIdentifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private final Users users;

    @Autowired
    public UsersService(Users users) {
        this.users = users;
    }

    public User retrieveTodo(UserIdentifier userIdentifier) {
        return users.retrieveUser(userIdentifier);
    }
}
