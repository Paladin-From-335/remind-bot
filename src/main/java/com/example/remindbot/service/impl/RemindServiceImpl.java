package com.example.remindbot.service.impl;

import com.example.remindbot.model.entity.Reminder;
import com.example.remindbot.repo.RemindRepo;
import com.example.remindbot.service.RemindService;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@PropertySource(value = "classpath:scheduler.properties")
public class RemindServiceImpl implements RemindService {

    private final RemindRepo remindRepo;

    @Override
    public void saveRemind(Reminder reminder) {
        remindRepo.save(reminder);
    }

    @Override
    @Scheduled(cron = "${cron.delete-old}")
    public void deleteOld() {
       this.remindRepo.deleteOld(Timestamp.valueOf(LocalDateTime.now(ZoneId.of("GMT"))));
    }

    @Override
    public List<Reminder> getActual() {
        return this.remindRepo.actualReminders(LocalDate.now(ZoneId.of("GMT")));
    }
}
