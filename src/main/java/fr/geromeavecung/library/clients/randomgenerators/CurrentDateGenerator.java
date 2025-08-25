package fr.geromeavecung.library.clients.randomgenerators;

import fr.geromeavecung.library.domain.boundedcontexts.shared.DateGenerator;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CurrentDateGenerator implements DateGenerator {

    @Override
    public LocalDate now() {
        return LocalDate.now();
    }

}
