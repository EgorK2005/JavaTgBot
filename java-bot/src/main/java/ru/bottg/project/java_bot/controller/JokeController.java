package ru.bottg.project.java_bot.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import ru.bottg.project.java_bot.model.Joke;
import ru.bottg.project.java_bot.service.JokeService;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/jokes")
public class JokeController {
    private final JokeService jokeService;

    public JokeController(JokeService jokeService) {
        this.jokeService = jokeService;
    }

    @GetMapping
    public Page<Joke> getAllJokes(@PageableDefault(size = 10) Pageable pageable) {
        return jokeService.findAll(pageable);
    }

    @PostMapping
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public Joke createJoke(@RequestBody Joke joke) {
        return jokeService.save(joke);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public Joke updateJoke(@PathVariable Long id, @RequestBody Joke joke) {
        return jokeService.update(id, joke);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteJoke(@PathVariable Long id) {
        jokeService.delete(id);
    }
}