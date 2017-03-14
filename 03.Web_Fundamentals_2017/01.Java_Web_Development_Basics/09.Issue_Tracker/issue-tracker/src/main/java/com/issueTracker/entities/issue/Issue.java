package com.issueTracker.entities.issue;

import com.issueTracker.entities.user.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "issues")
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "created_on", updatable = false)
    private Date createdOn;

    @ManyToOne
    @JoinColumn(name = "author", updatable = false)
    private User author;

    public Issue() {
    }

    public Issue(String name,
                 Priority priority,
                 Status status,
                 Date createdOn,
                 User author) {
        this();
        this.name = name;
        this.priority = priority;
        this.status = status;
        this.createdOn = createdOn;
        this.author = author;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Priority getPriority() {
        return this.priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public User getAuthor() {
        return this.author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
