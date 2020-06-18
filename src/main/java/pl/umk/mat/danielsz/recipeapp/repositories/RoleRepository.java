package pl.umk.mat.danielsz.recipeapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.umk.mat.danielsz.recipeapp.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
