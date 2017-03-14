package com.issueTracker.repositories.implementations;

import com.issueTracker.entities.issue.Issue;
import com.issueTracker.repositories.IssueRepository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class IssueRepositoryImpl implements IssueRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Issue issue) {
        this.entityManager.persist(issue);
    }

    @Override
    public void edit(Issue issue) {
        this.entityManager.merge(issue);
    }

    @Override
    public void delete(long id) {
        Query query = this.entityManager.createQuery("DELETE FROM Issue AS i " +
                "WHERE i.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public List<Issue> getAllIssues() {
        Query query = this.entityManager.createQuery("SELECT i FROM Issue AS i");
        return query.getResultList();
    }


    @Override
    public Issue findById(long id) {
        Issue issue = this.entityManager.find(Issue.class, id);
        return issue;
    }
}
