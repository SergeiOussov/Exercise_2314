package ru.bootstrap_demo.exercise_2314.services;

import org.springframework.stereotype.Component;
import ru.bootstrap_demo.exercise_2314.models.Role;
import ru.bootstrap_demo.exercise_2314.repositories.RoleRepository;
import java.util.List;

@Component
public class RoleServiceImp implements RoleService{

    private final RoleRepository roleRepository;

    public RoleServiceImp(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> allRoles() {
        return roleRepository.findAll();
    }
}
