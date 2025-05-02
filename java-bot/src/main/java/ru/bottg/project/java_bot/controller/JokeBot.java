package ru.bottg.project.java_bot.controller;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.bottg.project.java_bot.model.Joke;
import ru.bottg.project.java_bot.service.JokeService;

public class JokeBot extends TelegramLongPollingBot {
    private final String botToken;
    private final String botUsername;
    private final JokeService jokeService;

    public JokeBot(String botToken, String botUsername, JokeService jokeService) {
        this.botToken = botToken;
        this.botUsername = botUsername;
        this.jokeService = jokeService;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }


    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Long userId = update.getMessage().getFrom().getId();
            String messageText = update.getMessage().getText();

            if ("/joke".equals(messageText)) {
                Joke randomJoke = jokeService.getRandomJoke();
                jokeService.registerJokeRequest(randomJoke.getId(), userId);

                sendMessage(update.getMessage().getChatId(), randomJoke.getText());
            }
        }
    }

    private void sendRandomJoke(long chatId) {
        Joke joke = jokeService.getRandomJoke();
        String response = joke != null ? joke.getText() : "Анекдоты закончились 😞";
        sendMessage(chatId, response);
    }

    private void sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}