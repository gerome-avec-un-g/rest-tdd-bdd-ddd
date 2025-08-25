package fr.geromeavecung.library.clients.randomgenerators;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class CurrentDateGeneratorTest {

    private final CurrentDateGenerator generator = new CurrentDateGenerator();

    @Test
    void generates_local_date_for_today() {
        LocalDate date = generator.now();

        assertThat(date).isEqualTo(LocalDate.now());
    }

}