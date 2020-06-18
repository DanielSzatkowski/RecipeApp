package pl.umk.mat.danielsz.recipeapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.umk.mat.danielsz.recipeapp.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
