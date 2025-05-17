package ru.bottg.project.java_bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bottg.project.java_bot.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
}