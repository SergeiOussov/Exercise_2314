package ru.bootstrap_demo.exercise_2314.configs;

import org.springframework.stereotype.Component;
import ru.bootstrap_demo.exercise_2314.models.Role;
import ru.bootstrap_demo.exercise_2314.models.User;
import ru.bootstrap_demo.exercise_2314.services.RoleService;
import ru.bootstrap_demo.exercise_2314.services.UserService;
import javax.annotation.PostConstruct;
import java.util.Set;


@Component
public class DatabaseInitializer {
    private final UserService userService;
    private final RoleService roleService;

    public DatabaseInitializer(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    //@PostConstruct
    public void AddFirstUser() {
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");
        roleService.saveRole(roleAdmin);
        roleService.saveRole(roleUser);
        User admin = new User("admin1", "adm", 20, "a1@mail.ru", "111",
                Set.of(roleAdmin, roleUser));
        userService.saveUser(admin);
    }

}
