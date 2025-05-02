package ru.bottg.project.java_bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.bottg.project.java_bot.model.Joke;  // Добавьте этот импорт
import ru.bottg.project.java_bot.model.JokeRequest;
import java.util.List;

public interface JokeRequestRepository extends JpaRepository<JokeRequest, Long> {

    List<JokeRequest> findByUserIdOrderByRequestTimeDesc(Long userId);

    @Query("SELECT jr.joke FROM JokeRequest jr GROUP BY jr.joke ORDER BY COUNT(jr) DESC LIMIT :limit")
    List<Joke> findTopPopularJokes(@Param("limit") int limit);
}