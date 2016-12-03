package com.jsonexercise.domain.dto.exportDto;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.Set;

public class SellerDto implements Serializable {

    @Expose
    private String firstName;

    @Expose
    private String lastName;

    @Expose
    private Set<SoldProductDto> soldProducts;

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

    public Set<SoldProductDto> getSoldProducts() {
        return this.soldProducts;
    }

    public void setSoldProducts(Set<SoldProductDto> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
