package com.jsonexercise.services.category;

import com.jsonexercise.domain.dto.exportDto.CategoryWithProductsDto;
import com.jsonexercise.domain.dto.importDto.CategoryJsonDto;
import com.jsonexercise.domain.entities.Category;
import com.jsonexercise.io.parsers.modelParsers.ModelParser;
import com.jsonexercise.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final ModelParser modelParser;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               ModelParser modelParser) {
        this.categoryRepository = categoryRepository;
        this.modelParser = modelParser;
    }

    @Override
    public void persistCategories(CategoryJsonDto[] categoryDtos) {
        List<Category> categories =
                this.modelParser.convertToEntity(categoryDtos, Category.class);
        this.categoryRepository.save(categories);
    }

    @Override
    public List<CategoryWithProductsDto> findCategoriesWithProducts() {
        List<Object[]> categoriesStats =
                this.categoryRepository.findCategoriesWithProducts();
        List<CategoryWithProductsDto> categoriesStatsDtos =
                new ArrayList<>();
        for (Object[] categoriesStat : categoriesStats) {
            CategoryWithProductsDto categoryDto = new CategoryWithProductsDto();
            String categoryName = (String) categoriesStat[0];
            Long count = (Long) categoriesStat[1];
            Double average = (Double) categoriesStat[2];
            BigDecimal revenue = (BigDecimal) categoriesStat[3];

            categoryDto.setCategory(categoryName);
            categoryDto.setProductsCount(count);
            categoryDto.setAveragePrice(average);
            categoryDto.setTotalRevenue(revenue);
            categoriesStatsDtos.add(categoryDto);
        }
        return categoriesStatsDtos;
    }
}