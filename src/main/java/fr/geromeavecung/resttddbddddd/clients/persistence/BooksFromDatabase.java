package fr.geromeavecung.resttddbddddd.clients.persistence;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.books.Book;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.books.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class BooksFromDatabase implements Books {

    @Repository
    public interface BooksRepository extends JpaRepository<BookEntity, UUID> {

        List<BookEntity> findAllByAuthorIdentifier(UUID authorIdentifier);
    }

    private final BooksRepository repository;

    public BooksFromDatabase(BooksRepository repository) {
        this.repository = repository;
    }


    @Override
    public void save(Book book) {
        repository.save(new BookEntity(book));
    }

    @Override
    public List<Book> findAllByAuthor(UUID authorIdentifier) {
        return repository.findAllByAuthorIdentifier(authorIdentifier).stream()
                .map(BookEntity::convert)
                .toList();
    }

}
