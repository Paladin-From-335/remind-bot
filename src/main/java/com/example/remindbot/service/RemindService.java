package com.example.remindbot.service;

import com.example.remindbot.model.Reminder;
import com.example.remindbot.repo.RemindRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
@PropertySource(value = "classpath:scheduler.properties")
public class RemindService {

    private final RemindRepo remindRepo;

    public void saveRemind(Reminder reminder) {
        remindRepo.save(reminder);
    }

    @Scheduled(cron = "${cron.delete-old}")
    public void deleteOld() {
        this.remindRepo.deleteOld(LocalDate.now());
    }

    @Scheduled(cron = "${cron.get-actual}")
    public void getActual() {
        this.remindRepo.actualReminders(LocalDate.now(ZoneId.of("GMT")));
    }
}
