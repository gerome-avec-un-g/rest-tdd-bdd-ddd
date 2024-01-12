package fr.geromeavecung.resttddbddddd.clients.rest;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.shared.UserIdentifier;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.users.User;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.users.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class UsersRestTemplate implements Users {

    private final RestTemplate restTemplate;

    @Autowired
    public UsersRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
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
