package app.repositories;

import app.domain.entities.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    @Query(value = "SELECT b FROM Book AS b " +
            "WHERE b.title = :title")
    Book findByTitle(@Param(value = "title") String title);

    List<Book> findByReleaseDateAfter(Date date);

    @Query(value = "SELECT b FROM Book AS b " +
            "INNER JOIN b.author AS a " +
            "WHERE a.firstName = 'George' AND a.lastName = 'Powell' " +
            "ORDER BY b.releaseDate DESC, b.title ASC")
    List<Book> findGeorgePowellBooks();
}