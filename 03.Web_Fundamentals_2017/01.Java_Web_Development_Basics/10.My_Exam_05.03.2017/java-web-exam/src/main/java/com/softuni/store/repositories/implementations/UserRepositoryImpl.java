package com.softuni.store.repositories.implementations;

import com.softuni.store.entities.user.User;
import com.softuni.store.repositories.UserRepository;

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
    public void delete(Long id) {
        Query query = this.entityManager.createQuery("DELETE FROM User AS u " +
                "WHERE u.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        Query query = this.entityManager.createQuery("SELECT u FROM User AS u " +
                "WHERE u.email = :email " +
                "AND u.password = :password");
        query.setParameter("email", email);
        query.setParameter("password", password);
        List<User> users = query.getResultList();
        User user = null;
        if (!users.isEmpty()) {
            user = users.stream().findFirst().get();
        }
        return user;
    }

    @Override
    public User findById(Long id) {
        return this.entityManager.find(User.class, id);
    }

    @Override
    public Long getUsersCount() {
        Query query = this.entityManager.createQuery("SELECT COUNT(u) FROM User AS u");
        Long count = (Long) query.getSingleResult();
        return count;
    }
}
