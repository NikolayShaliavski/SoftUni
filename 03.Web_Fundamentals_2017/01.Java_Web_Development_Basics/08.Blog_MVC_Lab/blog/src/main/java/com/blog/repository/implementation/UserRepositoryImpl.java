package com.blog.repository.implementation;

import com.blog.entities.User;
import com.blog.repository.UserRepository;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@Local(UserRepository.class)
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createUser(User user) {
        this.entityManager.persist(user);
    }

    @Override
    public User retrieve(Long id) {
        Query query = this.entityManager.createQuery("SELECT u FROM User AS u WHERE u.id = :id");
        query.setParameter("id", id);
        return (User) query.getSingleResult();
    }

    @Override
    public User retrieve(String email) {
        Query query = this.entityManager.createQuery("SELECT u FROM User AS u WHERE u.email = :email");
        query.setParameter("email", email);
        return (User) query.getSingleResult();
    }

    @Override
    public Boolean exists(String email) {
        Query query = this.entityManager.createQuery("SELECT u FROM User AS u WHERE u.email = :email");
        query.setParameter("email", email);
        User user = (User) query.getSingleResult();
        return user != null;
    }
}
