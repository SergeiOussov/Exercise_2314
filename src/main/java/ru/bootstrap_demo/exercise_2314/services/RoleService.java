package ru.bootstrap_demo.exercise_2314.services;

import ru.bootstrap_demo.exercise_2314.models.Role;
import java.util.List;

public interface RoleService {
    List<Role> allRoles();

    void saveRole(Role roleAdmin);
}
