package pl.umk.mat.danielsz.recipeapp.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.umk.mat.danielsz.recipeapp.model.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    @Query(value = "SELECT r FROM Recipe r JOIN FETCH r.user WHERE UPPER(r.name) LIKE CONCAT('%', UPPER(:name), '%')",
            countQuery = "SELECT COUNT(r) FROM Recipe r WHERE UPPER(r.name) LIKE CONCAT('%', UPPER(:name), '%')")
    Page<Recipe> getAllByName(@Param("name") String name, Pageable pageable);

    @Query(value = "SELECT r FROM Recipe r JOIN FETCH r.user u WHERE u.id=:id",
            countQuery = "SELECT COUNT(r) FROM Recipe r JOIN r.user u WHERE u.id=:id")
    Page<Recipe> findAllByUserId(@Param("id") Long id, Pageable pageable);

    @Query(value = "SELECT r FROM Recipe r JOIN FETCH r.user u",
            countQuery = "SELECT count(r) FROM Recipe r")
    Page<Recipe> findAllFetchUsers(Pageable pageable);
}
