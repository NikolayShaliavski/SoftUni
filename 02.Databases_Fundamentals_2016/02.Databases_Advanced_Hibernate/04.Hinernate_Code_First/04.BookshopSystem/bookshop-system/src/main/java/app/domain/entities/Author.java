package app.domain.entities;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement(name = "author")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "authors")
public class Author implements Serializable {

    @XmlAttribute(name = "id")
    @Expose
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Long authorId;

    @XmlElement(name = "firstname")
    @Expose
    @Column(nullable = true, name = "first_name")
    private String firstName;

    @XmlElement(name = "lastname")
    @Expose
    @Column(name = "last_name")
    private String lastName;

    @XmlElementWrapper(name = "books")
    @XmlElement(name = "book")
    @Expose
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author", fetch = FetchType.EAGER)
    private Set<Book> books;

    public Author() {
        this.setBooks(new HashSet<>());
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Book> getBooks() {
        //return Collections.unmodifiableSet(this.books);
        return this.books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        this.books.add(book);
    }
}
