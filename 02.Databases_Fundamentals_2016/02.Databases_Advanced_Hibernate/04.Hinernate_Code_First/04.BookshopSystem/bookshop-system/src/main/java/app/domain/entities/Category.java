package app.domain.entities;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "categories")
public class Category implements Serializable {

    @Expose
    @XmlAttribute(name = "category-id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @Expose
    @XmlElement(name = "name")
    @Basic
    private String name;

    @XmlTransient
    @OneToMany(mappedBy = "category", targetEntity = Book.class, fetch = FetchType.EAGER)
    private Set<Book> books;

    public Category() {
        this.setBooks(new HashSet<>());
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
