package com.issueTracker.controllers;

import com.issueTracker.models.bindingModels.issue.IssueAddModel;
import com.issueTracker.models.bindingModels.issue.IssueEditModel;
import com.issueTracker.models.viewModels.issue.IssueEditViewModel;
import com.issueTracker.models.viewModels.issue.IssueViewModel;
import com.issueTracker.models.viewModels.user.LoggedUserModel;
import com.issueTracker.services.IssueService;
import com.mvcFramework.annotations.controller.Controller;
import com.mvcFramework.annotations.parameters.ModelAttribute;
import com.mvcFramework.annotations.parameters.PathVariable;
import com.mvcFramework.annotations.request.GetMapping;
import com.mvcFramework.annotations.request.PostMapping;
import com.mvcFramework.models.Model;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.List;

@Stateless
@Controller
public class IssueController {

    @Inject
    private IssueService issueService;

    @GetMapping("/issue")
    public String getIssuesPage(Model model, HttpSession session) {
        LoggedUserModel loggedUser = (LoggedUserModel) session.getAttribute("currentUser");
        List<IssueViewModel> issueViews = this.issueService.getAllIssues();

        model.addAttribute("loggedUser", loggedUser);
        model.addAttribute("issueViews", issueViews);
        return "/templates/issues";
    }

    @GetMapping("/issue/add")
    public String getAddIssuePage() {
        return "/templates/add-issue";
    }

    @PostMapping("/issue/add")
    public String addIssue(@ModelAttribute IssueAddModel issueAddModel, HttpSession session) {
        LoggedUserModel loggedUser = (LoggedUserModel) session.getAttribute("currentUser");
        String userName = loggedUser.getUsername();
        this.issueService.create(issueAddModel, userName);
        return "redirect:/issue";
    }

    @GetMapping("/issue/edit/{id}")
    public String getEditIssuePage(@PathVariable("id") long id, Model model) {
        IssueEditViewModel issueEditModel = this.issueService.getIssueById(id);
        model.addAttribute("issue", issueEditModel);
        return "/templates/edit-issue";
    }

    @PostMapping("/issue/edit/{id}")
    public String editIssue(@PathVariable("id") long id, @ModelAttribute IssueEditModel issueEditModel) {
        issueEditModel.setId(id);
        this.issueService.edit(issueEditModel);
        return "redirect:/issue";
    }

    @GetMapping("/issue/delete/{id}")
    public String deleteIssue(@PathVariable("id") long id) {
        this.issueService.delete(id);
        return "redirect:/issue";
    }
}
