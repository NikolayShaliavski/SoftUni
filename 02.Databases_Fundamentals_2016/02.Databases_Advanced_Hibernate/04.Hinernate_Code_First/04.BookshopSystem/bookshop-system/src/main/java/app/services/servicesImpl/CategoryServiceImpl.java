package app.services.servicesImpl;

import app.domain.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import app.repositories.CategoryRepository;
import app.services.CategoryService;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void save(Category category) {
        this.categoryRepository.saveAndFlush(category);
    }

    @Override
    public List<Category> findAll() {
        return this.categoryRepository.findAll();
    }
}