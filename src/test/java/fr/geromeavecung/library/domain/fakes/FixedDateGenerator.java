package fr.geromeavecung.library.domain.fakes;

import fr.geromeavecung.library.domain.boundedcontexts.shared.DateGenerator;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class FixedDateGenerator implements DateGenerator {

    @Override
    public LocalDate now() {
        return LocalDate.now();
    }

}
