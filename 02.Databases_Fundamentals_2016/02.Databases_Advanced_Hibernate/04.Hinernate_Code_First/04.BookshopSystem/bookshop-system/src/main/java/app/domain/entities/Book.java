package app.domain.entities;

import app.domain.enums.AgeRestriction;
import app.domain.enums.EditionType;
import com.google.gson.annotations.Expose;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement(name = "book")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "books")
public class Book implements Serializable {

    @Expose
    @XmlAttribute(name = "book-id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;

    @Expose
    @XmlElement(name = "title")
    @Basic
    private String title;

    @Expose
    @XmlElement(name = "description")
    @Column(nullable = true, length = 1000, columnDefinition = "TEXT")
    private String description;

    @Expose
    @XmlElement(name = "edition-type")
    @Enumerated(EnumType.STRING)
    @Column(name = "edition_type")
    private EditionType editonType;

    @Expose
    @XmlElement(name = "age-restriction")
    @Enumerated(EnumType.STRING)
    @Column(name = "age_restriction")
    private AgeRestriction ageRestriction;

    @Expose
    @XmlElement(name = "price")
    @Basic
    private BigDecimal price;

    @Expose
    @XmlElement(name = "copies")
    @Basic
    private Integer copies;

    @Expose
    @XmlElement(name = "release-date")
    @Column(nullable = true, name = "release_date")
    private Date releaseDate;

    @XmlTransient
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @Expose
    @XmlElement(name = "category")
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @XmlTransient
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "related_books",
    joinColumns = @JoinColumn(name = "book_id"),
    inverseJoinColumns = @JoinColumn(name = "related_id"))
    private Set<Book> relatedBooks;

    public Book() {
        this.setRelatedBooks(new HashSet<>());
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title.length() < 1 || title.length() > 50) {
            throw new IllegalArgumentException("Title must be between 1 and 50 symbols.");
        }
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AgeRestriction getAgeRestriction() {
        return this.ageRestriction;
    }

    public void setAgeRestriction(AgeRestriction ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public EditionType getEditonType() {
        return editonType;
    }

    public void setEditonType(EditionType editonType) {
        this.editonType = editonType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getCopies() {
        return copies;
    }

    public void setCopies(Integer copies) {
        this.copies = copies;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Book> getRelatedBooks() {
        return relatedBooks;
    }

    public void setRelatedBooks(Set<Book> relatedBooks) {
        this.relatedBooks = relatedBooks;
    }
}
