package fr.geromeavecung.resttddbddddd.domain.steps;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.books.Book;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.books.BookTitle;
import fr.geromeavecung.resttddbddddd.domain.fakes.BooksInMemory;
import fr.geromeavecung.resttddbddddd.domain.usecases.CreateABook;
import fr.geromeavecung.resttddbddddd.domain.usecases.CreateABookCommand;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateABookStepDefs {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateABookStepDefs.class);

    private final CreateABook createABook;
    private final BooksInMemory books;
    private final SharedState sharedState;

    private Book book;

    public CreateABookStepDefs(CreateABook createABook, BooksInMemory books, SharedState sharedState) {
        this.createABook = createABook;
        this.books = books;
        this.sharedState = sharedState;
    }

    @Given("the book {string} with its unique identifier {word} for author {word}")
    public void theBookWithItsUniqueIdentifierAedEbFFbEFffFForAuthorBbDafCCFABeAeAAc(String title, String bookIdentifier, String authorIdentifier) {
        books.save(new Book(UUID.fromString(bookIdentifier), new BookTitle(title), UUID.fromString(authorIdentifier)));
    }

    @When("I create a book titled {string} for author {word}")
    public void i_create_a_book_titled_for_author(String title, String authorIdentifier) {
        try {
            book = createABook.execute(new CreateABookCommand(SharedStepDefs.sanitize(title), authorIdentifier));
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
            sharedState.setException(exception);
        }
    }

    @Then("the book {string} is created with its unique identifier {word} for author {word}")
    public void the_book_is_created_with_its_unique_identifier_for_author(String title, String bookIdentifier, String authorIdentifier) {
        assertThat(books.findAll()).contains(book);
        assertThat(book).isEqualTo(new Book(UUID.fromString(bookIdentifier), new BookTitle(title), UUID.fromString(authorIdentifier)));
    }

}
