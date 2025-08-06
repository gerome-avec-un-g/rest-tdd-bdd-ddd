package fr.geromeavecung.library.clients.persistence;

import fr.geromeavecung.library.domain.boundedcontexts.authors.Author;
import fr.geromeavecung.library.domain.boundedcontexts.authors.Authors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class AuthorsFromDatabase implements Authors {

    @Repository
    public interface AuthorsRepository extends JpaRepository<AuthorEntity, UUID> {

        Optional<AuthorEntity> findByFirstNameAndLastName(String firstName, String lastName);

    }

    private final AuthorsRepository repository;

    public AuthorsFromDatabase(AuthorsRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Author author) {
        repository.save(new AuthorEntity(author));
    }

    // TODO dto projection

    @Override
    public List<Author> findAll() {
        return repository.findAll().stream()
                .map(AuthorEntity::convert)
                .toList();
    }

    @Override
    public Optional<Author> find(Author author) {
        return repository.findByFirstNameAndLastName(author.firstName(), author.lastName())
                .map(AuthorEntity::convert);
    }

    @Override
    public Optional<Author> find(UUID identifier) {
        return repository.findById(identifier)
                .map(AuthorEntity::convert);
    }
}
