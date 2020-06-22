package pl.umk.mat.danielsz.recipeapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.umk.mat.danielsz.recipeapp.exceptions.NotFoundException;
import pl.umk.mat.danielsz.recipeapp.exceptions.UnauthorizedAccessException;
import pl.umk.mat.danielsz.recipeapp.model.Comment;
import pl.umk.mat.danielsz.recipeapp.model.Recipe;
import pl.umk.mat.danielsz.recipeapp.model.User;
import pl.umk.mat.danielsz.recipeapp.repositories.CommentRepository;

import javax.validation.constraints.NotNull;

@Service
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final RecipeService recipeService;

    private void checkAuthorization(String userLogin, Long commentId){
        User userByLogin = userService.getByLogin(userLogin);
        User commentAuthor = userService.findCommentAuthorByCommentId(commentId);

        if(!userByLogin.getId().equals(commentAuthor.getId())) {
            throw new UnauthorizedAccessException("Access denied.");
        }
    }

    private void checkPaginationError(Pageable pageable, Page<Comment> commentPage){
        if(pageable.getPageNumber() > commentPage.getTotalPages()){
            throw new NotFoundException("Page doesn't exist.");
        } else if(commentPage.isEmpty()){
            throw new NotFoundException("Comments don't exist.");
        }
    }

    @Autowired
    public CommentService(CommentRepository commentRepository, UserService userService, RecipeService recipeService){
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.recipeService = recipeService;
    }

    public Page<Comment> getUsersComments(Long id, Pageable pageable) {
        Page<Comment> commentPage = commentRepository.findAllByUserId(id, pageable);

        checkPaginationError(pageable, commentPage);

        return commentPage;
    }

    public Page<Comment> getRecipesComments(Long id, Pageable pageable) {
        Page<Comment> commentPage = commentRepository.getAllByRecipeId(id, pageable);

        checkPaginationError(pageable, commentPage);

        return commentPage;
    }

    public Comment create(Comment commentToAdd, String userLogin, Long recipeId) {
        User user = userService.getByLogin(userLogin);
        Recipe recipe = recipeService.findOneById(recipeId);

        commentToAdd.setUser(user);
        commentToAdd.setRecipe(recipe);

        return commentRepository.save(commentToAdd);
    }

    public void deleteById(@NotNull Long commentId, String userLogin) {
        checkAuthorization(userLogin, commentId);

        commentRepository.deleteById(commentId);
    }
}
