package com.issueTracker.models.bindingModels.issue;

public class IssueEditModel {

    private long id;

    private String name;

    private String status;

    private String priority;

    public String getName() {
        return this.name;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return this.priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
