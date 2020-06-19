package pl.umk.mat.danielsz.recipeapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.umk.mat.danielsz.recipeapp.model.Recipe;
import pl.umk.mat.danielsz.recipeapp.services.RecipeService;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService){
        this.recipeService = recipeService;
    }

    @GetMapping
    public List<Recipe> getAll(Pageable pageable) {
        Page<Recipe> resultRecipes = recipeService.getAll(pageable);

        return resultRecipes.getContent();
    };

    @GetMapping("/{name}")
    public List<Recipe> getAllByName(@PathVariable String name, Pageable pageable) {
        Page<Recipe> resultRecipes = recipeService.getAllByName(name, pageable);

        return resultRecipes.getContent();
    };

    @GetMapping("/user/{id}")
    public List<Recipe> getAllByUserId(@PathVariable Long id, Pageable pageable) {
        Page<Recipe> resultRepcipes = recipeService.getAllByUserId(id, pageable);

        return resultRepcipes.getContent();
    };
}
