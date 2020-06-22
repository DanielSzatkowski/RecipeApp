package pl.umk.mat.danielsz.recipeapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.umk.mat.danielsz.recipeapp.exceptions.NotFoundException;
import pl.umk.mat.danielsz.recipeapp.model.Recipe;
import pl.umk.mat.danielsz.recipeapp.model.User;
import pl.umk.mat.danielsz.recipeapp.repositories.RecipeRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final UserService userService;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository, UserService userService){
        this.recipeRepository = recipeRepository;
        this.userService = userService;
    }

    public Page<Recipe> getAll(Pageable pageable) {
        Page<Recipe> resultRecipes = recipeRepository.findAllFetchUsers(pageable);

        if(pageable.getPageNumber() > resultRecipes.getTotalPages()){
            //TODO: ERROR page > total pages
        } else if(resultRecipes.isEmpty()){
            //TODO: ERROR empty return
        }

        return resultRecipes;
    }

    public Page<Recipe> getAllByName(String name, Pageable pageable) {
        Page<Recipe> resultRecipes = recipeRepository.getAllByName(name, pageable);

        if(pageable.getPageNumber() > resultRecipes.getTotalPages()){
            //TODO: ERROR page > total pages
        } else if(resultRecipes.isEmpty()){
            //TODO: ERROR empty return
        }

        return resultRecipes;
    }

    public Page<Recipe> getAllByUserId(Long id, Pageable pageable) {
        Page<Recipe> resultRecipes = recipeRepository.findAllByUserId(id, pageable);

        return resultRecipes;
    }

    public Recipe create(Recipe recipe, String login) {
        User user = userService.getByLogin(login);

        recipe.setUser(user);
        return recipeRepository.save(recipe);
    }

    public Recipe updateOrCreate(Long recipeId, Recipe recipeModifyInfo, String login) {
        Optional<Recipe> recipeOptional = recipeRepository.findOneById(recipeId);

        if(recipeOptional.isEmpty()){
            return create(recipeModifyInfo, login);
        } else {
            recipeModifyInfo.setId(recipeId);

            return recipeRepository.save(recipeModifyInfo);
        }
    }

    public Recipe findOneById(Long recipeId) {
        return recipeRepository.findOneById(recipeId)
            .orElseThrow(() -> new NotFoundException("Recipe having specified id doesn't exist."));
    }

    public void deleteById(Long recipeId) {
        recipeRepository.deleteById(recipeId);
    }
}
