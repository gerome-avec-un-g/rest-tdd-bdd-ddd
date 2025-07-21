package fr.geromeavecung.resttddbddddd.clients.rest;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.shared.UserIdentifier;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.users.User;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.users.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class UsersFromRest implements Users {

    private final RestTemplate restTemplate;

    @Autowired
    public UsersFromRest(RestTemplate jsonPlaceholderRestTemplate) {
        this.restTemplate = jsonPlaceholderRestTemplate;
    }

    @Override
    public User retrieveUser(UserIdentifier userIdentifier) {
        UserJson userJson = restTemplate.getForObject("/users/" + userIdentifier.value(), UserJson.class);
        if (userJson == null) {
            return null;
        }
        return userJson.toDomain();
    }
}
