package pl.umk.mat.danielsz.recipeapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.umk.mat.danielsz.recipeapp.exceptions.NotFoundException;
import pl.umk.mat.danielsz.recipeapp.model.Role;
import pl.umk.mat.danielsz.recipeapp.repositories.RoleRepository;

@Service
@Transactional
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    public Role getByName(String name){
        return roleRepository.findOneByNameIgnoringCase(name)
                .orElseThrow(() -> new NotFoundException("Role " + name + " doesn't exist."));
    }
}
