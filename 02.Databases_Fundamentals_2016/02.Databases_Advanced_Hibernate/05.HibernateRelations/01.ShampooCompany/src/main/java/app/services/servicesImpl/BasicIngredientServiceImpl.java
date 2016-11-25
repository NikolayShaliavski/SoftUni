package app.services.servicesImpl;

import app.domain.ingredients.BasicIngredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import app.repositories.BasicIngredientRepository;
import app.services.BasicIngredientService;

@Service
@Transactional
public class BasicIngredientServiceImpl implements BasicIngredientService {

    @Autowired
    private BasicIngredientRepository basicIngredientRepository;

    @Override
    public void create(BasicIngredient basicIngredient) {
        this.basicIngredientRepository.saveAndFlush(basicIngredient);
    }
}