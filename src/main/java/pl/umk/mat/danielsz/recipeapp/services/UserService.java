package pl.umk.mat.danielsz.recipeapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.umk.mat.danielsz.recipeapp.exceptions.NotFoundException;
import pl.umk.mat.danielsz.recipeapp.model.User;
import pl.umk.mat.danielsz.recipeapp.repositories.UserRepository;
import pl.umk.mat.danielsz.recipeapp.utils.AppContext;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final AppContext appContext;

    @Autowired
    public UserService(UserRepository userRepository, AppContext appContext) {
        this.userRepository = userRepository;
        this.appContext = appContext;
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
}
