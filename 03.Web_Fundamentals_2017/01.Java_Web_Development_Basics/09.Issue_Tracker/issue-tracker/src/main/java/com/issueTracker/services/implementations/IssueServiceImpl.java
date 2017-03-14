package com.issueTracker.services.implementations;

import com.issueTracker.entities.issue.Issue;
import com.issueTracker.entities.issue.Priority;
import com.issueTracker.entities.issue.Status;
import com.issueTracker.entities.user.User;
import com.issueTracker.models.bindingModels.issue.IssueAddModel;
import com.issueTracker.models.bindingModels.issue.IssueEditModel;
import com.issueTracker.models.viewModels.issue.IssueEditViewModel;
import com.issueTracker.models.viewModels.issue.IssueViewModel;
import com.issueTracker.repositories.IssueRepository;
import com.issueTracker.repositories.UserRepository;
import com.issueTracker.services.IssueService;
import com.issueTracker.utils.parser.interfaces.ModelParser;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Stateless
public class IssueServiceImpl implements IssueService {

    @Inject
    private IssueRepository issueRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private ModelParser modelParser;

    @Override
    public void create(IssueAddModel issueModel, String userName) {
        Issue issue = this.modelParser.convert(issueModel, Issue.class);
        issue.setPriority(Priority.valueOf(issueModel.getPriority()));
        issue.setStatus(Status.valueOf(issueModel.getStatus()));
        issue.setCreatedOn(new Date());

        User user = this.userRepository.findByUserName(userName);
        issue.setAuthor(user);
        this.issueRepository.create(issue);
    }

    @Override
    public void delete(long id) {
        this.issueRepository.delete(id);
    }

    @Override
    public void edit(IssueEditModel issueEditModel) {
        Issue issue = this.modelParser.convert(issueEditModel, Issue.class);
        issue.setStatus(Status.valueOf(issueEditModel.getStatus()));
        issue.setPriority(Priority.valueOf(issueEditModel.getPriority()));
        this.issueRepository.edit(issue);
    }

    @Override
    public List<IssueViewModel> getAllIssues() {
        List<Issue> allAdminIssues = this.issueRepository.getAllIssues();
        List<IssueViewModel> allAdminIssueViews = this.convertIssuesToViews(allAdminIssues);
        return allAdminIssueViews;
    }

    @Override
    public IssueEditViewModel getIssueById(long id) {
        Issue issue = this.issueRepository.findById(id);
        IssueEditViewModel issueEditModel = this.modelParser.convert(issue, IssueEditViewModel.class);
        issueEditModel.setPriority(issue.getPriority().name());
        issueEditModel.setStatus(issue.getStatus().name());
        return issueEditModel;
    }

    private List<IssueViewModel> convertIssuesToViews(List<Issue> issues) {
        List<IssueViewModel> issueViews = new ArrayList<>();
        for (Issue issue : issues) {
            IssueViewModel view = this.modelParser.convert(issue, IssueViewModel.class);
            view.setPriority(issue.getPriority().name());
            view.setStatus(issue.getStatus().name());
            issueViews.add(view);
        }
        return issueViews;
    }
}
