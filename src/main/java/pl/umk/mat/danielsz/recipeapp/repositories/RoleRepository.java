package pl.umk.mat.danielsz.recipeapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.umk.mat.danielsz.recipeapp.model.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findOneByNameIgnoringCase(String name);
}
