package fr.geromeavecung.resttddbddddd.domain.boundedcontexts.users;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.shared.UserIdentifier;

public interface Users {

    User retrieveUser(UserIdentifier userIdentifier);

}
