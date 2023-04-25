package ru.bootstrap_demo.exercise_2314.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.bootstrap_demo.exercise_2314.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("Select u from User u left join fetch u.roles where u.username=:username")
    Optional<User> findByUsername(String username);
}
