package app.services;

import app.domain.entities.Book;

import java.util.Date;
import java.util.List;

public interface BookService {

    void save(Book book);

    Book findByTitle(String title);

    Book findOne(Long id);

    List<Book> findAll();

    List<Book> findBooksAfterDate(Date date);

    List<Book> findGeorgePowellBooks();
}