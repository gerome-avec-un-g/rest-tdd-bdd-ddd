package fr.geromeavecung.resttddbddddd.clients.randomgenerators;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.shared.IdentifierGenerator;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RandomIdentifierGenerator implements IdentifierGenerator {

    @Override
    public UUID generate() {
        return UUID.randomUUID();
    }

}
