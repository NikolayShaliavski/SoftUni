package com.issueTracker.repositories;

import com.issueTracker.entities.user.User;

public interface UserRepository {

    void register(User user);

    User findByUsernameAndPassword(String username, String password);

    User findByUserName(String userName);
    long getUsersCount();
}
