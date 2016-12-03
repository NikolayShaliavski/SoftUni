package com.jsonexercise.services.category;

import com.jsonexercise.domain.dto.exportDto.CategoryWithProductsDto;
import com.jsonexercise.domain.dto.importDto.CategoryJsonDto;

import java.util.List;

public interface CategoryService {

    void persistCategories(CategoryJsonDto[] categoryDtos);

    List<CategoryWithProductsDto> findCategoriesWithProducts();
}