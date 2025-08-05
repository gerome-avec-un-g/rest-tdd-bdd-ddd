package fr.geromeavecung.resttddbddddd.domain.steps;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.books.Book;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.books.BookTitle;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.books.ISBN;
import fr.geromeavecung.resttddbddddd.domain.fakes.BooksInMemory;
import fr.geromeavecung.resttddbddddd.domain.usecases.CreateABook;
import fr.geromeavecung.resttddbddddd.domain.usecases.CreateABookCommand;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Year;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateABookStepDefs {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateABookStepDefs.class);

    private final CreateABook createABook;
    private final BooksInMemory booksInMemory;
    private final SharedState sharedState;

    private Book book;

    public CreateABookStepDefs(CreateABook createABook, BooksInMemory booksInMemory, SharedState sharedState) {
        this.createABook = createABook;
        this.booksInMemory = booksInMemory;
        this.sharedState = sharedState;
    }

    @DataTableType
    public Book book(Map<String, String> row) {
        // TODO ? Book have a String constructor ? or use request/command(can't no UUID?)?
        return new Book(new ISBN(row.get("ISBN")), new BookTitle(row.get("title")), Year.parse(row.get("publication date")), UUID.fromString(row.get("author identifier")));
    }

    @Given("the book {string} published in {word} with ISBN {word} for author {word}")
    public void theBookWithItsUniqueIdentifierAedEbFFbEFffFForAuthorBbDafCCFABeAeAAc(String title, String publicationDate, String isbn, String authorIdentifier) {
        booksInMemory.save(new Book(new ISBN(isbn), new BookTitle(title), Year.parse(publicationDate), UUID.fromString(authorIdentifier)));
    }

    @Given("the following books in the system")
    public void the_following_books_in_the_system(List<Book> books) {
        books.forEach(booksInMemory::save);
    }

    @When("I create a book titled {string} published in {word} with ISBN {word} for author {word}")
    public void i_create_a_book_titled_for_author(String title, String publicationDate, String isbn, String authorIdentifier) {
        try {
            book = createABook.execute(new CreateABookCommand(isbn, SharedStepDefs.sanitize(title), publicationDate, authorIdentifier));
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
            sharedState.setException(exception);
        }
    }

    @Then("the book {string} published in {word} is created with ISBN {word} for author {word}")
    public void the_book_is_created_with_its_unique_identifier_for_author(String title, String publicationDate, String isbn, String authorIdentifier) {
        assertThat(booksInMemory.findAll()).contains(book);
        assertThat(book).isEqualTo(new Book(new ISBN(isbn), new BookTitle(title), Year.parse(publicationDate), UUID.fromString(authorIdentifier)));
    }

}
