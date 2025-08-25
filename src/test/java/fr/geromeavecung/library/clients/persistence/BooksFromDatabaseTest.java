package fr.geromeavecung.library.clients.persistence;

import fr.geromeavecung.library.domain.boundedcontexts.books.Book;
import fr.geromeavecung.library.domain.boundedcontexts.books.BookTitle;
import fr.geromeavecung.library.domain.boundedcontexts.books.ISBN;
import fr.geromeavecung.library.domain.boundedcontexts.books.PublicationDate;
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
@Import({BooksFromDatabase.class})
@ContextConfiguration(classes = {PersistenceConfiguration.class})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BooksFromDatabaseTest {

    private static final Book FOUNDATION = new Book(new ISBN("978-0553293357"), new BookTitle("Foundation"), PublicationDate.read(1951), UUID.fromString("c6625e54-d4e8-4ba0-942e-d285839527e1"));
    private static final Book PRELUDE_TO_FOUNDATION = new Book(new ISBN("978-0385233132"), new BookTitle("Prelude to Foundation"), PublicationDate.read(1988), UUID.fromString("c6625e54-d4e8-4ba0-942e-d285839527e1"));

    @Autowired
    private BooksFromDatabase booksFromDatabase;

    @Autowired
    private BooksFromDatabase.BooksRepository repository;

    @Test
    void save() {
        booksFromDatabase.save(FOUNDATION);

        assertThat(repository.findAll().stream().map(BookEntity::convert)).contains((FOUNDATION));
    }

    @Nested
    class Find_All_Books_For_Author {

        @Test
        void find_all_book_for_author_with_0_books() {
            List<Book> actualBooks = booksFromDatabase.findAllByAuthor(UUID.fromString("c6625e54-d4e8-4ba0-942e-d285839527e1"));

            assertThat(actualBooks).isEmpty();
        }

        @Test
        void find_all_book_for_author_with_1_book() {
            booksFromDatabase.save(FOUNDATION);

            List<Book> actualBooks = booksFromDatabase.findAllByAuthor(UUID.fromString("c6625e54-d4e8-4ba0-942e-d285839527e1"));

            assertThat(actualBooks).containsExactlyInAnyOrder(FOUNDATION);
        }

        @Test
        void find_all_book_for_author_with_2_books() {
            booksFromDatabase.save(FOUNDATION);
            booksFromDatabase.save(PRELUDE_TO_FOUNDATION);

            List<Book> actualBooks = booksFromDatabase.findAllByAuthor(UUID.fromString("c6625e54-d4e8-4ba0-942e-d285839527e1"));

            assertThat(actualBooks).containsExactlyInAnyOrder(FOUNDATION, PRELUDE_TO_FOUNDATION);
        }

    }

    @Nested
    class Find_Book_By_Identifier {

        @Test
        void find_book_by_identifier_existing() {
            booksFromDatabase.save(FOUNDATION);

            Optional<Book> actualBooks = booksFromDatabase.findByIdentifier(new ISBN("978-0553293357"));

            assertThat(actualBooks).hasValue(FOUNDATION);
        }

        @Test
        void find_book_by_identifier_not_existing() {
            Optional<Book> actualBooks = booksFromDatabase.findByIdentifier(new ISBN("000-0000000000"));

            assertThat(actualBooks).isEmpty();
        }

    }

}