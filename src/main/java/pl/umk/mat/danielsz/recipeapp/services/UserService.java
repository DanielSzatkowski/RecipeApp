package pl.umk.mat.danielsz.recipeapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.umk.mat.danielsz.recipeapp.model.User;
import pl.umk.mat.danielsz.recipeapp.repositories.UserRepository;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getOneById(Long id) {
        User userResult =  userRepository.findById(id)
                .orElseThrow();             //TODO: obsluga bledow

        return userResult;
    }
}
