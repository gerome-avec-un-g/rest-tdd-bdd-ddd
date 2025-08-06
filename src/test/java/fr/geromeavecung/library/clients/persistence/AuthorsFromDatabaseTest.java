package fr.geromeavecung.library.clients.persistence;

import fr.geromeavecung.library.domain.boundedcontexts.authors.Author;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({AuthorsFromDatabase.class})
@ContextConfiguration(classes = {PersistenceConfiguration.class})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AuthorsFromDatabaseTest {

    @Autowired
    private AuthorsFromDatabase authorsFromDatabase;

    @Autowired
    private AuthorsFromDatabase.AuthorsRepository repository;

    @Test
    void save() {
        Author author = new Author(UUID.fromString("1160aed8-eb2f-4fb3-92e4-43480fff64f5"), "John", "Doe");

        authorsFromDatabase.save(author);

        assertThat(repository.findAll().stream().map(AuthorEntity::convert)).contains(author);
    }

    @Nested
    class Read_By_Name {

        @Test
        void read_by_name_one_existing_author() {
            Author author = new Author(UUID.fromString("1160aed8-eb2f-4fb3-92e4-43480fff64f5"), "John", "Doe");
            authorsFromDatabase.save(author);

            Optional<Author> existingAuthor = authorsFromDatabase.find(author);

            assertThat(existingAuthor).hasValue(author);
        }

        @Test
        void read_by_name_one_non_existing_author() {
            Author author = new Author(UUID.fromString("1160aed8-eb2f-4fb3-92e4-43480fff64f5"), "John", "Doe");
            authorsFromDatabase.save(author);
            Author newAuthor = new Author(UUID.fromString("c6625e54-d4e8-4ba0-942e-d285839527e1"), "Jane", "Doe");

            Optional<Author> existingAuthor = authorsFromDatabase.find(newAuthor);

            assertThat(existingAuthor).isEmpty();
        }

    }

    @Nested
    class Read_By_Identifier {

        @Test
        void read_by_identifier_one_existing_author() {
            Author author = new Author(UUID.fromString("1160aed8-eb2f-4fb3-92e4-43480fff64f5"), "John", "Doe");
            authorsFromDatabase.save(author);

            Optional<Author> existingAuthor = authorsFromDatabase.find(UUID.fromString("1160aed8-eb2f-4fb3-92e4-43480fff64f5"));

            assertThat(existingAuthor).hasValue(author);
        }

        @Test
        void read_by_identifier_one_non_existing_author() {
            Author author = new Author(UUID.fromString("1160aed8-eb2f-4fb3-92e4-43480fff64f5"), "John", "Doe");
            authorsFromDatabase.save(author);

            Optional<Author> existingAuthor = authorsFromDatabase.find(UUID.fromString("c6625e54-d4e8-4ba0-942e-d285839527e1"));

            assertThat(existingAuthor).isEmpty();
        }

    }

    @Nested
    class Read_All_Authors {

        @Test
        void read_all_authors_with_0_authors() {
            List<Author> existingAuthors = authorsFromDatabase.findAll();

            assertThat(existingAuthors).isEmpty();
        }

        @Test
        void read_all_authors_with_1_author() {
            Author author1 = new Author(UUID.fromString("c6625e54-d4e8-4ba0-942e-d285839527e1"), "Jane", "Doe");
            authorsFromDatabase.save(author1);

            List<Author> existingAuthors = authorsFromDatabase.findAll();

            assertThat(existingAuthors).containsExactlyInAnyOrder(author1);
        }

        @Test
        void read_all_authors_with_2_authors() {
            Author author1 = new Author(UUID.fromString("c6625e54-d4e8-4ba0-942e-d285839527e1"), "Jane", "Doe");
            authorsFromDatabase.save(author1);
            Author author2 = new Author(UUID.fromString("1160aed8-eb2f-4fb3-92e4-43480fff64f5"), "John", "Doe");
            authorsFromDatabase.save(author2);

            List<Author> existingAuthors = authorsFromDatabase.findAll();

            assertThat(existingAuthors).containsExactlyInAnyOrder(author1, author2);
        }

    }

}