package com.blog.repository;

import com.blog.entities.User;

public interface UserRepository {

    void createUser(User user);

    User retrieve(Long id);

    User retrieve(String email);

    Boolean exists(String email);
}
