package ru.bottg.project.java_bot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.bottg.project.java_bot.model.Joke;

public interface JokeRepository extends JpaRepository<Joke, Long> {
}