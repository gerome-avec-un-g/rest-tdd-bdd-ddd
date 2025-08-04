package fr.geromeavecung.resttddbddddd.domain.fakes;

import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.books.Book;
import fr.geromeavecung.resttddbddddd.domain.boundedcontexts.books.Books;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BooksInMemory implements Books {

    List<Book> books = new ArrayList<>();

    @Override
    public void save(Book book) {
        books.add(book);
    }

    public List<Book> findAll() {
        return books;
    }
}
