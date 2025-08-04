package fr.geromeavecung.resttddbddddd.domain.boundedcontexts.books;

import org.springframework.stereotype.Service;

@Service
public class BookCreation {

    private final Books books;

    public BookCreation(Books books) {
        this.books = books;
    }

    public Book save(Book book) {
        books.save(book);
        return book;
    }

}
