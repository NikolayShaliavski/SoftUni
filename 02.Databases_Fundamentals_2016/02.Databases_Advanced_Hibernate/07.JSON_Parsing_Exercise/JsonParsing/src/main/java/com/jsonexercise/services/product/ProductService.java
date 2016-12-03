package com.jsonexercise.services.product;

import com.jsonexercise.domain.dto.exportDto.ProductInRangeDto;
import com.jsonexercise.domain.dto.importDto.ProductJsonDto;

import java.util.List;

public interface ProductService {

    void persistProduct(ProductJsonDto[] productDtos);

    List<ProductInRangeDto> findAllWithPriceBetween();
}