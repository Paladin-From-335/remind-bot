package com.example.remindbot.service.impl;

import static com.example.remindbot.utils.DateTimeUtil.isEqualsOrElapsed;
import static com.example.remindbot.utils.ResponseBuilder.buildResponse;

import com.example.remindbot.config.DataWrapperConfig;
import com.example.remindbot.model.entity.Reminder;
import com.example.remindbot.service.BotFaceService;
import com.example.remindbot.service.SendService;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendServiceImpl implements SendService {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(100);
    private final DataWrapperConfig data;
    private final BotFaceService botFaceService;

    @Async("taskExecutor")
    public CompletableFuture<Void> sendReminder(Reminder reminder) {
        for (int i = 0; i < 3; i++) {
            try {
                botFaceService.sendMsg(buildResponse(reminder.getChatId(), reminder.getRemindText()));
                Thread.sleep(3000);
            } catch (TelegramApiException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        return CompletableFuture.completedFuture(null);
    }

    @Override
    @Scheduled(cron = "${cron.send-reminder}")
    public void checkAndSend() {
        data.getReminders().getTodaySReminders().forEach(
                r -> {
                    if (isEqualsOrElapsed(r.getCreatedTo())) {
                        scheduler.schedule(() -> sendReminder(r), 0, TimeUnit.SECONDS);
                        data.getReminders().removeTodaySReminder(r);
                    }
                });
    }
}
