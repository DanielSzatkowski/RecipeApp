package pl.umk.mat.danielsz.recipeapp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pl.umk.mat.danielsz.recipeapp.model.Recipe;
import pl.umk.mat.danielsz.recipeapp.model.dto.RecipePostDto;
import pl.umk.mat.danielsz.recipeapp.services.RecipeService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    public Recipe dtoToRecipe(RecipePostDto recipePostDto){
        Recipe recipe = new ModelMapper().map(recipePostDto, Recipe.class);

        return recipe;
    }

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

    @PostMapping
    public Recipe create(@RequestBody @Valid @NotNull RecipePostDto recipePostDto, Principal principal) {
        Recipe recipe = dtoToRecipe(recipePostDto);

        String login = principal.getName();

        return recipeService.create(recipe, login);
    }
}
