package com.jsonexercise.domain.dto.exportDto;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class UserDto implements Serializable {

    @Expose
    private String firstName;

    @Expose
    private String lastName;

    @Expose
    private Integer age;

    @Expose
    private UserProductDto soldProducts;

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public UserProductDto getSoldProducts() {
        return this.soldProducts;
    }

    public void setSoldProducts(UserProductDto soldProducts) {
        this.soldProducts = soldProducts;
    }
}
