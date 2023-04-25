package ru.bootstrap_demo.exercise_2314.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bootstrap_demo.exercise_2314.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
