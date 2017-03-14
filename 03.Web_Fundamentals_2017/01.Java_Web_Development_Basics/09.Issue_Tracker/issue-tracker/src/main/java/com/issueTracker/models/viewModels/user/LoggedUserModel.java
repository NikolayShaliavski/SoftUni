package com.issueTracker.models.viewModels.user;

import com.issueTracker.entities.user.Role;
import com.issueTracker.models.viewModels.issue.IssueViewModel;

import java.util.Set;

public class LoggedUserModel {

    private String username;

    private Role role;

    private Set<IssueViewModel> issues;

    public LoggedUserModel() {
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<IssueViewModel> getIssues() {
        return this.issues;
    }

    public void setIssues(Set<IssueViewModel> issues) {
        this.issues = issues;
    }
}
