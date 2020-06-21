package pl.umk.mat.danielsz.recipeapp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pl.umk.mat.danielsz.recipeapp.model.Comment;
import pl.umk.mat.danielsz.recipeapp.model.dto.CommentCreateDto;
import pl.umk.mat.danielsz.recipeapp.services.CommentService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    private Comment dtoToComment(CommentCreateDto commentCreateDto) {
        Comment comment = new ModelMapper().map(commentCreateDto, Comment.class);

        return comment;
    }

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/user/{id}")
    public List<Comment> getUsersComments(@PathVariable Long id, Pageable pageable) {
        Page<Comment> commentPage = commentService.getUsersComments(id, pageable);

        return commentPage.getContent();
    }

    @GetMapping("recipe/{id}")
    public List<Comment> getRecipesComments(@PathVariable Long id, Pageable pageable){
        Page<Comment> commentPage = commentService.getRecipesComments(id, pageable);

        return commentPage.getContent();
    }

    @PostMapping("/recipe/{recipeId}")
    public Comment postCommentForRecipe(@PathVariable Long recipeId, @RequestBody @Valid @NotNull CommentCreateDto commentCreateDto,
                                        Principal principal) {

        String userLogin = principal.getName();
        Comment commentToAdd = dtoToComment(commentCreateDto);

        return commentService.create(commentToAdd, userLogin, recipeId);
    }

    //TODO: DELETE
    //TODO: PUT
}
