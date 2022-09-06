package com.example.remindbot.service;

import static com.example.remindbot.utils.Command.CREATE;
import static com.example.remindbot.utils.Command.HELP;
import static com.example.remindbot.utils.Command.START;
import static com.example.remindbot.utils.DateTimeUtil.DATE_TIME_FORMATTER;
import static com.example.remindbot.utils.Phrases.CREATED_REMIND;
import static com.example.remindbot.utils.Phrases.CREATING_TEXT;
import static com.example.remindbot.utils.Phrases.CREATING_TIME;
import static com.example.remindbot.utils.Phrases.GREETINGS;
import static com.example.remindbot.utils.Phrases.HELP_INFO;
import static com.example.remindbot.utils.Phrases.INCORRECT_DATE_FORMAT;
import static com.example.remindbot.utils.Phrases.WRONG_TIME_EXCEPTION;

import com.example.remindbot.model.Reminder;
import com.example.remindbot.utils.Command;
import com.example.remindbot.utils.exception.OldDateException;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application-telegram.properties")
public class TelegramService extends TelegramLongPollingBot {

    private Message requestMessage = new Message();
    private SendMessage response = new SendMessage();
    private Map<Long, Reminder> remindMap = new ConcurrentHashMap<>();
    private String botUsername;
    private String botToken;
    private Map<Long, Reminder> todayRemindMap = new ConcurrentHashMap<>();
    private Command commands;

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

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        response.setChatId(requestMessage.getChatId());
        String userMessage = requestMessage.getText();
        doEvent(userMessage, update);
    }

    private void defaultMsg(SendMessage response, String msg) throws TelegramApiException {
        response.enableHtml(true);
        response.setText(msg);
        execute(response);
    }

    @Scheduled(fixedDelay = 5000)
    @SneakyThrows
    private void sendRemind() {
        for (Long key : todayRemindMap.keySet()) {
            SendMessage sm = new SendMessage();
            sm.setChatId(key);
            LocalDateTime ldt = todayRemindMap.get(key).getCreatedTo();
            String currentTime = LocalDateTime.now(ZoneId.of("GMT")).format(DATE_TIME_FORMATTER);
            LocalDateTime currentLdt = LocalDateTime.parse(currentTime, DATE_TIME_FORMATTER);
            if (currentLdt.isEqual(ldt) || ldt.isBefore(currentLdt)) {
                for (int i = 0; i < 3; i++) {
                    defaultMsg(sm, todayRemindMap.get(key).getRemindText());
                    Thread.sleep(10000);
                }
                todayRemindMap.remove(key);
            }
        }
    }

    private void doEvent(String userMessage, Update update) throws TelegramApiException {
        requestMessage = update.getMessage();
        if (update.hasMessage() && requestMessage.hasText()) {
            System.out.println(update.getMessage().getText() + ", chatID: " + requestMessage.getChatId());
            //main commands start
            if (userMessage.equals(HELP.toString())) {
                defaultMsg(response, HELP_INFO.toString());
            }
            if (userMessage.equals(START.toString())) {
                defaultMsg(response, GREETINGS.toString());
            }
            if (userMessage.equals(CREATE.toString())) {
                defaultMsg(response, CREATING_TEXT.toString());
                if (remindMap.get(requestMessage.getChatId()) != null) {
                    remindMap.remove(requestMessage.getChatId());
                }
            }
            //main commands end

            //reminder creating
            if (!userMessage.startsWith("/")) {
                if (remindMap.get(requestMessage.getChatId()) == null) {
                    remindMap.put(requestMessage.getChatId(), new Reminder(
                            userMessage,
                            requestMessage.getChatId(),
                            LocalDateTime.parse(LocalDateTime.now(ZoneId.of("GMT"))
                                    .format(DATE_TIME_FORMATTER), DATE_TIME_FORMATTER)));
                    defaultMsg(response, CREATING_TIME.toString());
                } else if (remindMap.get(requestMessage.getChatId()).getRemindText() != null) {
                    try {
                        if (LocalDateTime.now(ZoneId.of("GMT")).isAfter(LocalDateTime.parse(userMessage, DATE_TIME_FORMATTER))) {
                            throw new OldDateException(
                                    String.format(
                                            WRONG_TIME_EXCEPTION.toString(),
                                            LocalDateTime.now(ZoneId.of("GMT")).format(DATE_TIME_FORMATTER)));
                        }
                        LocalDateTime time = LocalDateTime.parse(userMessage, DATE_TIME_FORMATTER);
                        remindMap.get(requestMessage.getChatId()).setCreatedTo(time);
                        defaultMsg(response, String.format(
                                CREATED_REMIND.toString(),
                                remindMap.get(requestMessage.getChatId()).getRemindText(),
                                remindMap.get(requestMessage.getChatId()).getCreatedTo()));
                    } catch (DateTimeParseException e) {
                        defaultMsg(response, INCORRECT_DATE_FORMAT.toString());
                    } catch (OldDateException e) {
                        defaultMsg(response, e.getMessage());
                    }
                }
                //saving to db
                if (Objects.nonNull(remindMap.get(requestMessage.getChatId()).getRemindText())
                        && Objects.nonNull(remindMap.get(requestMessage.getChatId()).getCreatedTo())) {
                    remindService.saveRemind(remindMap.get(requestMessage.getChatId()));
                }
                //saving actual to todayRemindMap
                LocalDateTime ld = remindMap.get(requestMessage.getChatId()).getCreatedTo();
                if (Objects.isNull(todayRemindMap.get(requestMessage.getChatId()))
                        && ld.toLocalDate().
                        equals(LocalDate.now())) {
                    todayRemindMap.put(requestMessage.getChatId(), remindMap.get(requestMessage.getChatId()));
                    System.out.println(todayRemindMap.keySet());
                }
                remindMap.remove(requestMessage.getChatId());
            }
        }
    }
}
