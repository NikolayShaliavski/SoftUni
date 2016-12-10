package com.validation.testEntities;

import com.validation.annotaions.Email;
import com.validation.annotaions.Password;

public class User {

    private Long id;

    private String name;

    @Password(minLength = 3,
            maxLength = 6,
            containsDigit = true,
            containsLowerCase = false)
    private String password;

    @Email(minLength = 5)
    private String email;

    public User() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
