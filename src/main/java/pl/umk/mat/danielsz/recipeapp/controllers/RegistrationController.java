package pl.umk.mat.danielsz.recipeapp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.umk.mat.danielsz.recipeapp.model.Role;
import pl.umk.mat.danielsz.recipeapp.model.User;
import pl.umk.mat.danielsz.recipeapp.model.dto.UserRegistrationDto;
import pl.umk.mat.danielsz.recipeapp.services.RoleService;
import pl.umk.mat.danielsz.recipeapp.services.UserService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@RestController
public class RegistrationController  {

    private final RoleService roleService;
    private final UserService userService;

    @Autowired
    public RegistrationController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostMapping("/registration")
    public User register(@RequestBody @Valid @NotNull UserRegistrationDto userRegistrationDto){
        //TODO: chceck if unique mail and login

        User user = new ModelMapper().map(userRegistrationDto, User.class);

        Set<Role> roles = new HashSet<>();
        Role role = roleService.getByName("ROLE_USER");
        roles.add(role);

        user.setRoles(roles);

        user = userService.register(user);

        return user;
    }
}
