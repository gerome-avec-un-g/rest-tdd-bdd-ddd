package fr.geromeavecung.resttddbddddd.clients.rest;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.shared.UserIdentifier;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.users.Address;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.users.City;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.users.Email;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.users.Name;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.users.Street;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.users.User;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.users.ZipCode;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(UsersFromRest.class)
@AutoConfigureWebClient(registerRestTemplate = true)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class UsersFromRestTest {

    @Autowired
    private UsersFromRest restTemplate;

    @Autowired
    private MockRestServiceServer mockServer;

    @Test
    void retrieve_1_user() {
        Address address = new Address(new Street("Kulas Light"), new City("Gwenborough"), new ZipCode("92998-3874"));
        var expected = new User(new UserIdentifier(1), new Name("Leanne Graham"),
                new Email("Sincere@april.biz"), address);
        mockServer.expect(requestTo("/users/1"))
                .andRespond(withSuccess("""
                        {
                           "id": 1,
                           "name": "Leanne Graham",
                           "username": "Bret",
                           "email": "Sincere@april.biz",
                           "address": {
                             "street": "Kulas Light",
                             "suite": "Apt. 556",
                             "city": "Gwenborough",
                             "zipcode": "92998-3874",
                             "geo": {
                               "lat": "-37.3159",
                               "lng": "81.1496"
                             }
                           },
                           "phone": "1-770-736-8031 x56442",
                           "website": "hildegard.org",
                           "company": {
                             "name": "Romaguera-Crona",
                             "catchPhrase": "Multi-layered client-server neural-net",
                             "bs": "harness real-time e-markets"
                           }
                         }""", MediaType.APPLICATION_JSON));

        User actual = restTemplate.retrieveUser(new UserIdentifier(1));

        mockServer.verify();
        assertThat(actual).isEqualTo(expected);
    }

}