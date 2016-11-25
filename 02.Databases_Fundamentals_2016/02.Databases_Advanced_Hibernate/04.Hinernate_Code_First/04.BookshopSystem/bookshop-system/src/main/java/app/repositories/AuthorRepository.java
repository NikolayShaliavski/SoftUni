package app.repositories;

import app.domain.entities.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {

    @Query(value = "SELECT a FROM Author AS a " +
            "INNER JOIN a.books AS b " +
            "WHERE b.releaseDate < :date")
    List<Author> findByBooksAfter(@Param(value = "date") Date date);

    @Query(value = "SELECT a.firstName, a.lastName, COUNT(b.bookId) FROM Author AS a " +
            "INNER JOIN a.books AS b " +
            "GROUP BY b.author " +
            "ORDER BY COUNT(b.bookId) DESC")
    List<Object[]> findAuthorsAndBooks();
}