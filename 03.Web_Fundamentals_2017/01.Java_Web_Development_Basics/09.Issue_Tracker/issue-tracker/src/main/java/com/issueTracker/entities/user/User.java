package com.issueTracker.entities.user;

import com.issueTracker.entities.issue.Issue;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username")
    private String username;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "author")
    private Set<Issue> issues;

    public User() {
        this.setIssues(new HashSet<>());
    }

    public User(String username,
                String fullName,
                String password,
                Role role) {
        this();
        this.username = username;
        this.fullName = fullName;
        this.password = password;
        this.role = role;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<Issue> getIssues() {
        return this.issues;
    }

    public void setIssues(Set<Issue> issues) {
        this.issues = issues;
    }
}
