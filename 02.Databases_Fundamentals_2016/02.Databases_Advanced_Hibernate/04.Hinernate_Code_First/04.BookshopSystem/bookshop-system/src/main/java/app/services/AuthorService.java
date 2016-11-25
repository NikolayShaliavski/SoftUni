package app.services;

import app.domain.entities.Author;

import java.util.Date;
import java.util.List;

public interface AuthorService {

    void save(Author author);

    List<Author> findAll();

    Author findOne(Long id);

    List<Author> findByBooksAfter(Date date);

    List<Object[]> findAuthorsAndBooks();
}