package app.services.servicesImpl;

import app.domain.entities.Book;
import app.repositories.BookRepository;
import app.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void save(Book book) {
        this.bookRepository.saveAndFlush(book);
    }

    @Override
    public Book findByTitle(String title) {
        return this.bookRepository.findByTitle(title);
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Book findOne(Long id) {
        return this.bookRepository.findOne(id);
    }

    @Override
    public List<Book> findBooksAfterDate(Date date) {
        return this.bookRepository.findByReleaseDateAfter(date);
    }

    @Override
    public List<Book> findGeorgePowellBooks() {
        return this.bookRepository.findGeorgePowellBooks();
    }
}