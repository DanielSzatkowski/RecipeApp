package pl.umk.mat.danielsz.recipeapp.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.umk.mat.danielsz.recipeapp.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Page<Comment> findAllByUserId(Long id, Pageable pageable);

    Page<Comment> getAllByRecipeId(Long id, Pageable pageable);
}
