package com.softuni.store.repositories;

import com.softuni.store.entities.user.User;

public interface UserRepository {

    void register(User user);

    void delete(Long id);

    User findByEmailAndPassword(String email, String password);

    User findById(Long id);

    Long getUsersCount();
}
