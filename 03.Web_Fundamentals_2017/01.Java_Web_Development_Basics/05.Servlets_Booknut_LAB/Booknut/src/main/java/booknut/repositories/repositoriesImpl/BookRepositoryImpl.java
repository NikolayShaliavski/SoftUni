package booknut.repositories.repositoriesImpl;

import booknut.entities.Book;
import booknut.repositories.BookRepository;

import java.util.ArrayList;
import java.util.List;

public class BookRepositoryImpl implements BookRepository {

    private static BookRepository bookRepository;

    private List<Book> books;

    private BookRepositoryImpl() {
        this.books = new ArrayList<>();
    }

    public static BookRepository getInstance() {
        if (bookRepository == null) {
            bookRepository = new BookRepositoryImpl();
        }
        return bookRepository;
    }
    @Override
    public void saveBook(Book book) {
        this.books.add(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return this.books;
    }

    @Override
    public Book findBookByTitle(String title) {
        return this.books.stream().
                filter(book -> book.getTitle().equals(title)).
                findAny().orElse(null);
    }

    @Override
    public void deleteBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                this.books.remove(book);
                break;
            }
        }
    }
}
