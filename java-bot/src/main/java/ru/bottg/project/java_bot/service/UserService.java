package ru.bottg.project.java_bot.service;

import ru.bottg.project.java_bot.model.User;

public interface UserService {
    User registerUser(String username, String password);
    void changeUserRole(Long userId, String roleName);
}