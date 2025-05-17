package ru.bottg.project.java_bot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bottg.project.java_bot.model.User;
import ru.bottg.project.java_bot.service.UserService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest request) {

        return userService.registerUser(request.username(), request.password());
    }

    public record RegisterRequest(String username, String password) {}
}