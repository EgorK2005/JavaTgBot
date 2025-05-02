package ru.bottg.project.java_bot.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.bottg.project.java_bot.model.Joke;
import ru.bottg.project.java_bot.model.JokeRequest;

import java.util.List;

public interface JokeService {

    Page<Joke> findAll(Pageable pageable);

    Joke getRandomJoke();

    Joke findById(Long id);

    Joke save(Joke joke);

    Joke update(Long id, Joke joke);

    void delete(Long id);

    JokeRequest registerJokeRequest(Long jokeId, Long userId);

    List<JokeRequest> getRequestHistoryByUser(Long userId);

    List<Joke> getTopPopularJokes(int limit);
}