package booknut.repositories;

import booknut.entities.Book;

import java.util.List;

public interface BookRepository {

    void saveBook(Book book);

    List<Book> getAllBooks();

    Book findBookByTitle(String title);

    void deleteBookByTitle(String title);
}
