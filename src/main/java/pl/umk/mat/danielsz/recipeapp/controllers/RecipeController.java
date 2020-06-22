package pl.umk.mat.danielsz.recipeapp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.umk.mat.danielsz.recipeapp.model.Recipe;
import pl.umk.mat.danielsz.recipeapp.model.dto.RecipeCreateAndUpdateDto;
import pl.umk.mat.danielsz.recipeapp.services.RecipeService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    public Recipe dtoToRecipe(RecipeCreateAndUpdateDto recipeCreateAndUpdateDto){
        Recipe recipe = new ModelMapper().map(recipeCreateAndUpdateDto, Recipe.class);

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
    public Recipe create(@RequestBody @Valid @NotNull RecipeCreateAndUpdateDto recipeCreateAndUpdateDto, Principal principal) {
        Recipe recipeModifyInfo = dtoToRecipe(recipeCreateAndUpdateDto);

        String login = principal.getName();

        return recipeService.create(recipeModifyInfo, login);
    }

    @PostMapping("/{recipeId}/picture")
    public Recipe updatepicture(@RequestParam("file") MultipartFile image, @PathVariable Long recipeId, Principal principal) {
        String userLogin = principal.getName();

        return recipeService.updatePicture(image, recipeId, userLogin);
    }

    @PutMapping("/{recipeId}")
    public Recipe updateOrCreate(@PathVariable Long recipeId, @RequestBody @Valid @NotNull RecipeCreateAndUpdateDto recipeCreateAndUpdateDto, Principal principal){
        Recipe recipeModifyInfo = dtoToRecipe(recipeCreateAndUpdateDto);

        String login = principal.getName();

        return recipeService.updateOrCreate(recipeId, recipeModifyInfo, login);
    }

    @DeleteMapping("/{recipeId}")
    public ResponseEntity<Void> remove(@PathVariable Long recipeId){
        recipeService.deleteById(recipeId);

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
