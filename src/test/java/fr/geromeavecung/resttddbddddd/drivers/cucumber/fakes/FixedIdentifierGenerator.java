package fr.geromeavecung.resttddbddddd.drivers.cucumber.fakes;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.shared.IdentifierGenerator;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class FixedIdentifierGenerator implements IdentifierGenerator {

    @Override
    public UUID generate() {
        return UUID.fromString("1160aed8-eb2f-4fb3-92e4-43480fff64f5");
    }

}
