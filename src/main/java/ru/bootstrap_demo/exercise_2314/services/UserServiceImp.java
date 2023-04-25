package ru.bootstrap_demo.exercise_2314.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.bootstrap_demo.exercise_2314.models.User;
import ru.bootstrap_demo.exercise_2314.repositories.RoleRepository;
import ru.bootstrap_demo.exercise_2314.repositories.UserRepository;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
public class UserServiceImp implements UserService{
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImp(UserRepository userRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User findUserById(Long userId) {
        Optional<User> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new User());
    }

    @Override
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public boolean saveUser(User user) {
        Optional<User> userFromDB = userRepository.findByUsername(user.getUsername());
        if (userFromDB.isPresent()) {
            return false;
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    @Transactional
    @Override
    public boolean updateUser(User user) {
        Optional<User> userFromDB = userRepository.findByUsername(user.getUsername());
        if (userFromDB.isPresent() && userFromDB.get().getId() != user.getId()) {
            return false;
        }
        if (user.getPassword().isEmpty()) {
            userFromDB = userRepository.findById(user.getId());
            userFromDB.ifPresent(value -> user.setPassword(value.getPassword()));
        } else {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        userRepository.save(user);
        return true;
    }

    @Transactional
    @Override
    public boolean deleteUserById(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }
}
