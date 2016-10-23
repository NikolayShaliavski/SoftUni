package entities;

import persistence.Column;
import persistence.Entity;
import persistence.Id;

import java.util.Date;

@Entity(name = "books")
public class Book {

    @Id
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "published_on")
    private Date publishedOn;

    @Column(name = "language")
    private String language;

    @Column(name = "rating")
    private int rating;

    @SuppressWarnings("unused")
    public Book() {
        super();
    }

    public Book(String title,
                String author,
                Date publishedOn,
                String language,
                int rating) {
        this.setTitle(title);
        this.setAuthor(author);
        this.setPublicationDate(publishedOn);
        this.setLanguage(language);
        this.setRating(rating);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublicationDate(Date publishedOn) {
        this.publishedOn = publishedOn;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return String.format(
                "Book id: %d, Title: %s, Author: %s, Published on: %s, Language: %s, Rating: %d",
                this.id, this.title, this.author, this.publishedOn, this.language, this.rating);
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getRating() {
        return this.rating;
    }
}
