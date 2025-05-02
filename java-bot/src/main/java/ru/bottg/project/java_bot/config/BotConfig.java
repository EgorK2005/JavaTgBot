package ru.bottg.project.java_bot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.bottg.project.java_bot.controller.JokeBot;
import ru.bottg.project.java_bot.service.JokeService;

@Configuration
public class BotConfig {
    @Value("${telegram.bot.token}")
    private String botToken;

    @Value("${telegram.bot.username}")
    private String botUsername;

    @Bean
    public TelegramBotsApi telegramBotsApi(JokeBot jokeBot) throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(jokeBot);
        return botsApi;
    }

    @Bean
    public JokeBot jokeBot(JokeService jokeService) {
        return new JokeBot(botToken, botUsername, jokeService);
    }
}