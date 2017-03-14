package com.issueTracker.services;

import com.issueTracker.models.bindingModels.issue.IssueAddModel;
import com.issueTracker.models.bindingModels.issue.IssueEditModel;
import com.issueTracker.models.viewModels.issue.IssueEditViewModel;
import com.issueTracker.models.viewModels.issue.IssueViewModel;

import java.util.List;

public interface IssueService {

    void create(IssueAddModel issueModel, String userName);

    void delete(long id);

    void edit(IssueEditModel issueEditModel);

    List<IssueViewModel> getAllIssues();

    IssueEditViewModel getIssueById(long id);
}
