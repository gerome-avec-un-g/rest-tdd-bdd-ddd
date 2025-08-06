package fr.geromeavecung.library.clients.randomgenerators;

import fr.geromeavecung.library.domain.boundedcontexts.shared.IdentifierGenerator;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RandomIdentifierGenerator implements IdentifierGenerator {

    @Override
    public UUID generate() {
        return UUID.randomUUID();
    }

}
