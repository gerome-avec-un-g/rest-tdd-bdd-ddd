package fr.geromeavecung.resttddbddddd.clients.persistence;

import fr.geromeavecung.resttddbddddd.ApplicationConfiguration;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.authors.Author;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({AuthorsFromDatabase.class})
@ContextConfiguration(classes = {ApplicationConfiguration.class})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AuthorsFromDatabaseTest {

    @Autowired
    private AuthorsFromDatabase authorsFromDatabase;

    @Autowired
    private AuthorsFromDatabase.AuthorsRepository authorsRepository;

    @Test
    void save() {
        Author author = new Author(UUID.fromString("1160aed8-eb2f-4fb3-92e4-43480fff64f5"), "John", "Doe");

        authorsFromDatabase.save(author);

        assertThat(authorsRepository.findAll().stream().map(AuthorEntity::convert)).contains(author);
    }

}