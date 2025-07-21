package fr.geromeavecung.resttddbddddd.domain.boundedcontexts.authors;

public record AuthorCreationCommand(String firstName, String lastName) {

    public Author convert() {
        return new Author(firstName, lastName);
    }
}
