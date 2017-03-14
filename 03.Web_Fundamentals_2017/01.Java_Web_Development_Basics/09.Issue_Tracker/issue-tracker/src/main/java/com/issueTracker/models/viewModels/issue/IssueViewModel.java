package com.issueTracker.models.viewModels.issue;

import com.issueTracker.models.viewModels.user.LoggedUserModel;

import java.util.Date;

public class IssueViewModel {

    private long id;

    private String name;

    private String priority;

    private String status;

    private Date createdOn;

    private LoggedUserModel author;

    public IssueViewModel() {
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

    public String getPriority() {
        return this.priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public LoggedUserModel getAuthor() {
        return this.author;
    }

    public void setAuthor(LoggedUserModel author) {
        this.author = author;
    }
}
