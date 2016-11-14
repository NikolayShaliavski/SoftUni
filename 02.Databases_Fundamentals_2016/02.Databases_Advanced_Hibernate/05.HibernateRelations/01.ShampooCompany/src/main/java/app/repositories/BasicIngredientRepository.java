package app.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import app.domain.ingredients.BasicIngredient;

@Repository
public interface BasicIngredientRepository extends JpaRepository<BasicIngredient,Long> {
}