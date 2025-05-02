package ru.bottg.project.java_bot.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bottg.project.java_bot.model.Joke;
import ru.bottg.project.java_bot.model.JokeRequest;
import ru.bottg.project.java_bot.repository.JokeRepository;
import ru.bottg.project.java_bot.repository.JokeRequestRepository;
import ru.bottg.project.java_bot.service.JokeService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class JokeServiceImpl implements JokeService {

    private final JokeRepository jokeRepository;
    private final JokeRequestRepository jokeRequestRepository;
    private final Random random = new Random();

    @Override
    @Transactional(readOnly = true)
    public Page<Joke> findAll(Pageable pageable) {
        return jokeRepository.findAll(pageable);
    }

    @Override
    public Joke getRandomJoke() {
        long count = jokeRepository.count();
        if (count == 0) {
            return createDefaultJoke();
        }
        return jokeRepository.findById(random.nextLong(count) + 1)
                .orElseGet(this::createDefaultJoke);
    }


    public Joke createDefaultJoke() {
        Joke joke = new Joke();
        joke.setText("Колобок повесился");
        return jokeRepository.save(joke);
    }

    @Override
    @Transactional(readOnly = true)
    public Joke findById(Long id) {
        return jokeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Joke not found with id: " + id));
    }

    @Override
    @Transactional
    public Joke save(Joke joke) {
        return jokeRepository.save(joke);
    }

    @Override
    @Transactional
    public Joke update(Long id, Joke jokeDetails) {
        Joke joke = findById(id);
        joke.setText(jokeDetails.getText());
        return jokeRepository.save(joke);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        jokeRepository.deleteById(id);
    }

    @Override
    public JokeRequest registerJokeRequest(Long jokeId, Long userId) {
        Joke joke = jokeRepository.findById(jokeId)
                .orElseThrow(() -> new EntityNotFoundException("Joke not found"));

        JokeRequest request = new JokeRequest();
        request.setJoke(joke);
        request.setUserId(userId);
        request.setRequestTime(LocalDateTime.now());

        return jokeRequestRepository.save(request);
    }
    @Override
    @Transactional(readOnly = true)
    public List<JokeRequest> getRequestHistoryByUser(Long userId) {
        return jokeRequestRepository.findByUserIdOrderByRequestTimeDesc(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Joke> getTopPopularJokes(int limit) {
        return jokeRequestRepository.findTopPopularJokes(limit);
    }
}
