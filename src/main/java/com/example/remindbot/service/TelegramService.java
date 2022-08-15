package com.example.remindbot.service;

import static com.example.remindbot.utils.CommandsEn.CREATE;
import static com.example.remindbot.utils.CommandsEn.START;
import static com.example.remindbot.utils.DateTimeUtil.DATE_TIME_FORMATTER;
import static com.example.remindbot.utils.Phrases.CREATING_TEXT;
import static com.example.remindbot.utils.Phrases.CREATING_TIME;
import static com.example.remindbot.utils.Phrases.GREETINGS;

import com.example.remindbot.model.Remind;
import com.example.remindbot.utils.exception.OldDateException;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application-telegram.properties")
public class TelegramService extends TelegramLongPollingBot {

    private Message requestMessage = new Message();
    private SendMessage response = new SendMessage();
    private Map<Long, Remind> remindMap = new HashMap<>();
    private final String botUsername;
    private final String botToken;

    private final RemindService remindService;

    public TelegramService(TelegramBotsApi telegramBotsApi,
                           @Value("${telegram-bot.name}") String botUsername,
                           @Value("${telegram-bot.token}") String botToken,
                           RemindService remindService) throws TelegramApiException {
        this.botToken = botToken;
        this.botUsername = botUsername;
        this.remindService = remindService;
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
        requestMessage = update.getMessage();
        response.setChatId(requestMessage.getChatId().toString());
        String userMessage = requestMessage.getText();
        if (update.hasMessage() && requestMessage.hasText()) {
            System.out.println(update.getMessage().getText() + ", chatID: " + requestMessage.getChatId());
        }

        if (userMessage.equals(START.toString())) {
            defaultMsg(response, GREETINGS.toString());

        } else if (userMessage.equals(CREATE.toString())) {
            defaultMsg(response, CREATING_TEXT.toString());

        } else if (!userMessage.startsWith("/")) {
            if (remindMap.get(requestMessage.getChatId()) == null) {
                remindMap.put(requestMessage.getChatId(), new Remind(userMessage, requestMessage.getChatId()));
                remindMap.get(requestMessage.getChatId()).setCreatedAt(LocalDateTime.now().toString());
                defaultMsg(response, CREATING_TIME.toString());
            } else if (remindMap.get(requestMessage.getChatId()).getRemindText() != null) {
                try {
                    if (LocalDateTime.now().isAfter(LocalDateTime.parse(userMessage, DATE_TIME_FORMATTER))) {
                        throw new OldDateException("Your date and time can't be earlier than current\n" +
                                "Current date and time: <i>" + LocalDateTime.now().format(DATE_TIME_FORMATTER) + "</i>");
                    }
                    String time = LocalDateTime.parse(userMessage, DATE_TIME_FORMATTER).format(DATE_TIME_FORMATTER);
                    remindMap.get(requestMessage.getChatId()).setCreatedTo(time);
                    defaultMsg(response, "<b>Your remind:</b> <i>" + remindMap.get(requestMessage.getChatId()).getRemindText() +
                            "</i>\n<b>Time:</b> <i>" + remindMap.get(requestMessage.getChatId()).getCreatedTo() + "</i>");
                } catch (DateTimeParseException e) {
                    defaultMsg(response, "Incorrect date format, try again.\n" +
                            "Example: 12:30 19.07.2025");
                } catch (OldDateException e) {
                    defaultMsg(response, e.getMessage());
                }
            }
            if (remindMap.get(requestMessage.getChatId()).getRemindText() != null && remindMap.get(requestMessage.getChatId()).getCreatedTo() != null) {
//                remindService.saveRemind(remindMap.get(requestMessage.getChatId()));
                remindMap.remove(requestMessage.getChatId());
            }

        }
    }

    private void defaultMsg(SendMessage response, String msg) {
       try {
           response.enableHtml(true);
           response.setText(msg);
           execute(response);
       }catch (TelegramApiException e) {
           e.printStackTrace();
       }
    }

}
