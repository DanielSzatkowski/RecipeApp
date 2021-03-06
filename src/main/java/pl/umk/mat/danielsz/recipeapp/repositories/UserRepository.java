package pl.umk.mat.danielsz.recipeapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.umk.mat.danielsz.recipeapp.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.login = :login")
    Optional<User> getByLoginFetchRoles(@Param("login") String login);

    Optional<User> findOneByLogin(String login);

    boolean existsByMail(String mail);

    @Query("SELECT u FROM User u JOIN u.comments c WHERE c.id = :commentId")
    Optional<User> findCommentAuthorByCommentId(@Param(value = "commentId") Long commentId);

    boolean existsByLogin(String login);
}
