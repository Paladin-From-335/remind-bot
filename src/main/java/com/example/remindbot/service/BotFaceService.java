package com.example.remindbot.service;

import static com.example.remindbot.utils.ResponseBuilder.buildResponse;

import com.example.remindbot.config.DataWrapperConfig;
import com.example.remindbot.model.constants.State;
import com.example.remindbot.model.entity.User;
import com.example.remindbot.service.handler.CallbackHandler;
import com.example.remindbot.service.handler.MessageHandler;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

@Component
@PropertySources({
        @PropertySource("classpath:application-telegram.properties"),
        @PropertySource("classpath:scheduler.properties")})
public class BotFaceService extends TelegramLongPollingBot {
    private final MessageHandler messageHandler;
    private final CallbackHandler callbackHandler;
    private final DataWrapperConfig data;
    private final String botUsername;
    private final String botToken;

    public BotFaceService(TelegramBotsApi telegramBotsApi,
                          @Value("${telegram-bot.name}") String botUsername,
                          @Value("${telegram-bot.token}") String botToken,
                          MessageHandler messageHandler,
                          CallbackHandler callbackHandler,
                          DataWrapperConfig data) throws TelegramApiException {
        this.botToken = botToken;
        this.botUsername = botUsername;
        this.messageHandler = messageHandler;
        this.callbackHandler = callbackHandler;
        this.data = data;
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

    @Override
    public void onUpdateReceived(Update update) {
        try {
            if (update.hasMessage() && update.getMessage().hasText()) {
                handleMessage(update);
            } else if (update.hasCallbackQuery()) {
                handleQuery(update);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleMessage(Update update) throws TelegramApiException {
        State state;
        String userMessage = update.getMessage().getText();
        long id = update.getMessage().getChatId();
        if (!data.getUserRepo().existsById(id)) {
            data.getUserRepo().saveAndFlush(new User(id));
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
                state = data.getStates().getLastState(id);
                break;
        }
        int msgId = sendMsg((SendMessage) messageHandler.handleMessage(update, state));
        data.getMsgIds().saveMsgId(id, msgId);
    }

    private void handleQuery(Update query) throws TelegramApiException {
        Long id = query.getCallbackQuery().getMessage().getChatId();
        if (data.getEvents().isEventExist(id)) {
            sendMsg(callbackHandler.handleCallback(query, data.getMsgIds().getMsgId(id)));
        } else {
            sendMsg(buildResponse(id));
        }
    }

    private Integer sendMsg(SendMessage response) throws TelegramApiException {
        response.enableHtml(true);
        return execute(response).getMessageId();
    }

    public void sendMsg(BotApiMethod<?> response) throws TelegramApiException {
        execute(response);
    }
}
