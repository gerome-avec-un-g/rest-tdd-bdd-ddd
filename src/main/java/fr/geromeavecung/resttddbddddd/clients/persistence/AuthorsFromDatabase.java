package fr.geromeavecung.resttddbddddd.clients.persistence;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.authors.Author;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.authors.Authors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class AuthorsFromDatabase implements Authors {

    @Repository
    public interface AuthorsRepository extends JpaRepository<AuthorEntity, UUID> {

    }

    public AuthorsFromDatabase(AuthorsRepository authorsRepository) {
        this.authorsRepository = authorsRepository;
    }

    private final AuthorsRepository authorsRepository;

    @Override
    public void save(Author author) {
        authorsRepository.save(new AuthorEntity(author));
    }

    @Override
    public List<Author> findAll() {
        return authorsRepository.findAll().stream()
                .map(AuthorEntity::convert)
                .toList();
    }
}
