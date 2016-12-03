package com.jsonexercise.domain.dto.exportDto;

import com.google.gson.annotations.Expose;
import com.jsonexercise.domain.dto.importDto.ProductJsonDto;

import java.io.Serializable;
import java.util.Set;

public class UserProductDto implements Serializable {

    @Expose
    private Integer count;

    @Expose
    private Set<ProductJsonDto> products;

    public Integer getCount() {
        return this.count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Set<ProductJsonDto> getProducts() {
        return this.products;
    }

    public void setProducts(Set<ProductJsonDto> products) {
        this.products = products;
    }
}
