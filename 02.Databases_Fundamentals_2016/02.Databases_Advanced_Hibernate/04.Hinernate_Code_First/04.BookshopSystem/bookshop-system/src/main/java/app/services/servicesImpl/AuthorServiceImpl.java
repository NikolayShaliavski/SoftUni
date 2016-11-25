package app.services.servicesImpl;

import app.domain.entities.Author;
import app.repositories.AuthorRepository;
import app.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public void save(Author author) {
        this.authorRepository.saveAndFlush(author);
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Author findOne(Long id) {
        return this.authorRepository.findOne(id);
    }

    @Override
    public List<Author> findByBooksAfter(Date date) {
        return this.authorRepository.findByBooksAfter(date);
    }

    @Override
    public List<Object[]> findAuthorsAndBooks() {
        return this.authorRepository.findAuthorsAndBooks();
    }
}