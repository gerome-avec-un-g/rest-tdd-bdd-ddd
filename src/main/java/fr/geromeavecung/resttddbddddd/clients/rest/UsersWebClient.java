package fr.geromeavecung.resttddbddddd.clients.rest;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.shared.UserIdentifier;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.users.User;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.users.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

@Repository
public class UsersWebClient implements Users {

    private final WebClient webClient;

    @Autowired
    public UsersWebClient(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public User retrieveUser(UserIdentifier userIdentifier) {
        UserJson userJson = Objects.requireNonNull(webClient.get().uri("/users/" + userIdentifier.value())
                .retrieve().toEntity(UserJson.class).block()).getBody();
        if (userJson == null) {
            return null;
        }
        return userJson.toDomain();
    }
}
