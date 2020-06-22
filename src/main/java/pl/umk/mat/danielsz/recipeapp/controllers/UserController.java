package pl.umk.mat.danielsz.recipeapp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.umk.mat.danielsz.recipeapp.model.User;
import pl.umk.mat.danielsz.recipeapp.model.dto.UserPatchDto;
import pl.umk.mat.danielsz.recipeapp.services.UserService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.security.Principal;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    private User dtoToUser(UserPatchDto userPatchDto) {
        User user = new ModelMapper().map(userPatchDto, User.class);

        return user;
    }

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public User getOneById(@PathVariable Long id){
        User userResult = userService.getOneById(id);

        return userResult;
    }

    @PatchMapping
    public User patch(@RequestBody @Valid @NotNull UserPatchDto userDto, Principal principal) {
        User user = dtoToUser(userDto);

        String login = principal.getName();

        return userService.patch(login, user);
    }

    @PostMapping("/picture")
    public User updatePicture(@RequestParam("file")MultipartFile file, Principal principal) {
        String userLogin = principal.getName();

        return userService.updatePicture(file, userLogin);
    }
}
