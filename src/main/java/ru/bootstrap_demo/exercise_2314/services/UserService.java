package ru.bootstrap_demo.exercise_2314.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.bootstrap_demo.exercise_2314.models.User;
import java.util.List;

public interface UserService extends UserDetailsService {
    User findUserById(Long userId);

    User findByEmail(String email);

    List<User> allUsers();

    boolean saveUser(User user);

    boolean updateUser(User user);

    boolean deleteUserById(Long userId);
}
