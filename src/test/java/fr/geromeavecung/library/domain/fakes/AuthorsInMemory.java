package fr.geromeavecung.library.domain.fakes;

import fr.geromeavecung.library.domain.boundedcontexts.authors.Author;
import fr.geromeavecung.library.domain.boundedcontexts.authors.Authors;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class AuthorsInMemory implements Authors {

    private final List<Author> values = new ArrayList<>();

    @Override
    public void save(Author author) {
        values.add(author);
    }

    public List<Author> findAll() {
        return values;
    }

    @Override
    public Optional<Author> find(Author author) {
        return values.stream()
                .filter(existingAuthor -> existingAuthor.firstName().equals(author.firstName())
                        && existingAuthor.lastName().equals(author.lastName()))
                .findFirst();
    }

    @Override
    public Optional<Author> find(UUID identifier) {
        return values.stream()
                .filter(existingAuthor -> existingAuthor.identifier().equals(identifier))
                .findFirst();
    }
}
