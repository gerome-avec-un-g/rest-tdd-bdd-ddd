package fr.geromeavecung.resttddbddddd.clients.persistence;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.books.Book;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.books.BookTitle;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({BooksFromDatabase.class})
@ContextConfiguration(classes = {PersistenceConfiguration.class})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BooksFromDatabaseTest {

    @Autowired
    private BooksFromDatabase booksFromDatabase;

    @Autowired
    private BooksFromDatabase.BooksRepository repository;

    @Test
    void save() {
        Book book = new Book(UUID.fromString("1160aed8-eb2f-4fb3-92e4-43480fff64f5"), new BookTitle("Foundation"), UUID.fromString("c6625e54-d4e8-4ba0-942e-d285839527e1"));

        booksFromDatabase.save(book);

        assertThat(repository.findAll().stream().map(BookEntity::convert)).contains((book));
    }

    @Nested
    class Read_All_Books_For_Author {

        @Test
        void read_all_book_for_author_with_0_books() {
            List<Book> actualBooks = booksFromDatabase.findAllByAuthor(UUID.fromString("c6625e54-d4e8-4ba0-942e-d285839527e1"));

            assertThat(actualBooks).isEmpty();
        }

        @Test
        void read_all_book_for_author_with_1_book() {
            Book book1 = new Book(UUID.fromString("1160aed8-eb2f-4fb3-92e4-43480fff64f5"), new BookTitle("Foundation"), UUID.fromString("c6625e54-d4e8-4ba0-942e-d285839527e1"));
            booksFromDatabase.save(book1);

            List<Book> actualBooks = booksFromDatabase.findAllByAuthor(UUID.fromString("c6625e54-d4e8-4ba0-942e-d285839527e1"));

            assertThat(actualBooks).containsExactlyInAnyOrder(book1);
        }

        @Test
        void read_all_book_for_author_with_2_books() {
            Book book1 = new Book(UUID.fromString("1160aed8-eb2f-4fb3-92e4-43480fff64f5"), new BookTitle("Foundation"), UUID.fromString("c6625e54-d4e8-4ba0-942e-d285839527e1"));
            booksFromDatabase.save(book1);
            Book book2 = new Book(UUID.fromString("589a0b4c-93b8-4f46-8c7e-02794a8c252e"), new BookTitle("Prelude to Foundation"), UUID.fromString("c6625e54-d4e8-4ba0-942e-d285839527e1"));
            booksFromDatabase.save(book2);

            List<Book> actualBooks = booksFromDatabase.findAllByAuthor(UUID.fromString("c6625e54-d4e8-4ba0-942e-d285839527e1"));

            assertThat(actualBooks).containsExactlyInAnyOrder(book1, book2);
        }

    }

}