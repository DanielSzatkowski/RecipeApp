package pl.umk.mat.danielsz.recipeapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.umk.mat.danielsz.recipeapp.model.Comment;
import pl.umk.mat.danielsz.recipeapp.repositories.CommentRepository;

@Service
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }

    public Page<Comment> getUsersComments(Long id, Pageable pageable) {
        Page<Comment> commentPage = commentRepository.findAllByUserId(id, pageable);

        if(pageable.getPageNumber() > commentPage.getTotalPages()){
            //TODO: Error page > total Page
        } else if(commentPage.isEmpty()){
            //TODO: empty list retruned
        }

        return commentPage;
    }

    public Page<Comment> getRecipesComments(Long id, Pageable pageable) {
        Page<Comment> commentPage = commentRepository.getAllByRecipeId(id, pageable);

        if(pageable.getPageNumber() > commentPage.getTotalPages()){
            //TODO: ERROR page >  total Page
        } else if(commentPage.isEmpty()){
            //TODO: ERROR empty return
        }

        return commentPage;
    }
}
