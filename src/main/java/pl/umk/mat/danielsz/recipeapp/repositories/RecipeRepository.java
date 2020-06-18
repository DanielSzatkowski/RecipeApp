package pl.umk.mat.danielsz.recipeapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.umk.mat.danielsz.recipeapp.model.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
