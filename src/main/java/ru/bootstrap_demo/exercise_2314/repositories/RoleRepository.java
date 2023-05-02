package ru.bootstrap_demo.exercise_2314.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bootstrap_demo.exercise_2314.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
