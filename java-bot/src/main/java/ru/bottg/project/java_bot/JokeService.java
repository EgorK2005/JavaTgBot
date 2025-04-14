package ru.bottg.project.java_bot.service;

import org.springframework.stereotype.Service;
import ru.bottg.project.java_bot.model.Joke;
import ru.bottg.project.java_bot.repository.JokeRepository;
import java.util.List;
import java.util.Optional;

@Service
public class JokeService {
    private final JokeRepository jokeRepository;

    public JokeService(JokeRepository jokeRepository) {
        this.jokeRepository = jokeRepository;
    }

    public List<Joke> getAllJokes() {
        return jokeRepository.findAll();
    }

    public Optional<Joke> getJokeById(Long id) {
        return jokeRepository.findById(id);
    }

    public Joke saveJoke(Joke joke) {
        return jokeRepository.save(joke);
    }

    public void deleteJoke(Long id) {
        jokeRepository.deleteById(id);
    }

    public Joke getRandomJoke() {
        List<Joke> jokes = getAllJokes();
        if (jokes.isEmpty()) return null;
        int randomIndex = (int) (Math.random() * jokes.size());
        return jokes.get(randomIndex);
    }
}