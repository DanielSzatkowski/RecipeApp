package pl.umk.mat.danielsz.recipeapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import pl.umk.mat.danielsz.recipeapp.services.UserService;

public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }
}
