package pl.umk.mat.danielsz.recipeapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.umk.mat.danielsz.recipeapp.exceptions.NotFoundException;
import pl.umk.mat.danielsz.recipeapp.exceptions.OperationNotAllowedException;
import pl.umk.mat.danielsz.recipeapp.model.User;
import pl.umk.mat.danielsz.recipeapp.repositories.UserRepository;
import pl.umk.mat.danielsz.recipeapp.utils.AppContext;

import javax.validation.constraints.NotNull;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final AppContext appContext;

    private User modifyUser(@NotNull User userToChange, @NotNull User user) {
        if(!userToChange.getMail().equals(user.getMail())
                && existsByMail(user.getMail())){

            throw new OperationNotAllowedException("Mail already in use.");
        }

        if(user.getMail() != null && !user.getMail().isBlank()) {
            userToChange.setMail(user.getMail());
        }

        if(user.getDescription() != null && !user.getDescription().isBlank()) {
            userToChange.setDescription(user.getDescription());
        }

        return userToChange;
    }

    @Autowired
    public UserService(UserRepository userRepository, AppContext appContext) {
        this.userRepository = userRepository;
        this.appContext = appContext;
    }

    public boolean existsByMail(String mail){
        return userRepository.existsByMail(mail);
    }

    public User getByLogin(String login){
        return userRepository.findOneByLogin(login)
                .orElseThrow(() -> new NotFoundException("User having specified login doesn't exist."));
    }

    public User getByLoginFetchRoles(String login){
        User user = userRepository.getByLoginFetchRoles(login)
                .orElseThrow(() -> new NotFoundException("Incorrect login"));

        return user;
    }

    public User getOneById(Long id) {
        User userResult =  userRepository.findById(id)
                .orElseThrow();             //TODO: obsluga bledow

        return userResult;
    }

    public User register(User user) {
        user.setPassword(appContext.getBean(PasswordEncoder.class).encode(user.getPassword()));

        return userRepository.save(user);
    }

    public User patch(String login, User user) {
        User userDb = getByLogin(login);

        User changedUser = modifyUser(userDb, user);

        return userRepository.save(changedUser);
    }

    public User findCommentAuthorByCommentId(Long commentId) {
        return userRepository.findCommentAuthorByCommentId(commentId)
            .orElseThrow(() -> new NotFoundException("Author of the specified comment was not found."));
    }
}
