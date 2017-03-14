package com.issueTracker.repositories.implementations;

import com.issueTracker.entities.user.User;
import com.issueTracker.repositories.UserRepository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void register(User user) {
        this.entityManager.persist(user);
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        Query query = this.entityManager.createQuery("SELECT u FROM User AS u " +
                "WHERE u.username = :username AND u.password = :password");
        query.setParameter("username", username);
        query.setParameter("password", password);
        List<User> users = query.getResultList();
        User user = null;
        if (!users.isEmpty()) {
            user = users.stream().findFirst().get();
        }
        return user;
    }

    @Override
    public User findByUserName(String userName) {
        Query query = this.entityManager.createQuery("SELECT u FROM User AS u " +
                "WHERE u.username = :username");
        query.setParameter("username", userName);
        List<User> users = query.getResultList();
        User user = null;
        if (!users.isEmpty()) {
            user = users.stream().findFirst().get();
        }
        return user;
    }

    @Override
    public long getUsersCount() {
        Query query = this.entityManager.createQuery("SELECT COUNT(u) FROM User AS u");
        long count = (long) query.getSingleResult();
        return count;
    }
}
