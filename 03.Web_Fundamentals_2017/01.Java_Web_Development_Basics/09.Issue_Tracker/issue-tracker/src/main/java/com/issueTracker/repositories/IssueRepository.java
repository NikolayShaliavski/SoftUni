package com.issueTracker.repositories;

import com.issueTracker.entities.issue.Issue;

import java.util.List;

public interface IssueRepository {

    void create(Issue issue);

    void edit(Issue issue);

    void delete(long id);

    List<Issue> getAllIssues();

    Issue findById(long id);
}
