package fr.geromeavecung.resttddbddddd.clients.randomgenerators;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class RandomIdentifierGeneratorTest {

    private final RandomIdentifierGenerator generator = new RandomIdentifierGenerator();

    @Test
    void generates_random_identifiers() {
        UUID identifier1 = generator.generate();
        UUID identifier2 = generator.generate();

        assertThat(identifier1).isNotEqualTo(identifier2);
    }
}