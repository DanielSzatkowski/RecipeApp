package pl.umk.mat.danielsz.recipeapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.umk.mat.danielsz.recipeapp.model.Comment;
import pl.umk.mat.danielsz.recipeapp.services.CommentService;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

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
}
