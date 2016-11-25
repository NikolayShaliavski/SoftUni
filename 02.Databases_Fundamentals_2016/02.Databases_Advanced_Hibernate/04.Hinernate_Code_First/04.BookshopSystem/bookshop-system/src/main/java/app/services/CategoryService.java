package app.services;

import app.domain.entities.Category;

import java.util.List;

public interface CategoryService {

    void save(Category category);

    List<Category> findAll();
}