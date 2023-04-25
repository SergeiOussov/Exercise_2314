package ru.bootstrap_demo.exercise_2314.services;

import ru.bootstrap_demo.exercise_2314.models.User;
import java.util.List;

public interface UserService {
    User findUserById(Long userId);

    List<User> allUsers();

    boolean saveUser(User user);

    boolean updateUser(User user);

    boolean deleteUserById(Long userId);
}
