package com.example.remindbot.service;

import com.example.remindbot.model.constants.State;
import com.example.remindbot.model.entity.User;
import com.example.remindbot.repo.UserRepo;
import com.example.remindbot.service.handler.CallbackHandler;
import com.example.remindbot.service.handler.MessageHandler;
import com.example.remindbot.utils.cash.StateCash;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application-telegram.properties")
public class BotFaceService extends TelegramLongPollingBot {

    private final MessageHandler messageHandler;
    private final CallbackHandler callbackHandler;
    private final StateCash stateCash;
    private final UserRepo userRepo;
    private String botUsername;
    private String botToken;

    public BotFaceService(TelegramBotsApi telegramBotsApi,
                          @Value("${telegram-bot.name}") String botUsername,
                          @Value("${telegram-bot.token}") String botToken,
                          MessageHandler messageHandler,
                          CallbackHandler callbackHandler,
                          StateCash stateCash, UserRepo userRepo) throws TelegramApiException {
        this.botToken = botToken;
        this.botUsername = botUsername;
        this.messageHandler = messageHandler;
        this.callbackHandler = callbackHandler;
        this.stateCash = stateCash;
        this.userRepo = userRepo;
        telegramBotsApi.registerBot(this);
    }

    @Override
    public String getBotUsername() {
        return this.botUsername;
    }

    @Override
    public String getBotToken() {
        return this.botToken;
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            handleMessage(update.getMessage().getText(), update);
        } else if (update.hasCallbackQuery()) {
            handleQuery(update);
        }
    }

    private void handleMessage(String userMessage, Update update) throws TelegramApiException {
        State state;
        long id = update.getMessage().getChatId();
        if (!userRepo.existsById(id)) {
            userRepo.saveAndFlush(new User(id));
        }
        switch (userMessage) {
            case "/start":
                state = State.START;
                break;
            case "Create new reminder \uD83D\uDD8A":
                state = State.CREATE;
                break;
            case "Help information \uD83C\uDD98":
                state = State.HELP;
                break;
            default:
                state = stateCash.getLastState(id);
                break;
        }
        sendMsg(messageHandler.handleMessage(update, state));
    }

    private void handleQuery(Update query) throws TelegramApiException {
        sendMsg((SendMessage) callbackHandler.handleCallback(query));
    }

    private void sendMsg(SendMessage response) throws TelegramApiException {
        response.enableHtml(true);
        execute(response);
    }

    //TODO GET METHOD ID
    private void sendMsg(BotApiMethod<?>... args) throws TelegramApiException {
        for (BotApiMethod<?> arg : args) {
            execute(arg);
        }
    }

    @Scheduled
    @SuppressWarnings("PLUG")
    void plug() {
    }
}
