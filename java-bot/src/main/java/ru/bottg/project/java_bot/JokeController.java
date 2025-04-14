package ru.bottg.project.java_bot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bottg.project.java_bot.model.Joke;
import ru.bottg.project.java_bot.service.JokeService;
import java.util.List;

@RestController
@RequestMapping("/jokes")
public class JokeController {
    private final JokeService jokeService;

    public JokeController(JokeService jokeService) {
        this.jokeService = jokeService;
    }

    @GetMapping
    public List<Joke> getAllJokes() {
        return jokeService.getAllJokes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Joke> getJokeById(@PathVariable Long id) {
        return jokeService.getJokeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Joke> createJoke(@RequestBody Joke joke) {
        return ResponseEntity.status(201).body(jokeService.saveJoke(joke));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Joke> updateJoke(@PathVariable Long id, @RequestBody Joke joke) {
        if (!jokeService.getJokeById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        joke.setId(id);
        return ResponseEntity.ok(jokeService.saveJoke(joke));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJoke(@PathVariable Long id) {
        if (!jokeService.getJokeById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        jokeService.deleteJoke(id);
        return ResponseEntity.noContent().build();
    }
}