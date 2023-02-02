package com.example.remindbot.service.impl;

import com.example.remindbot.model.entity.Reminder;
import com.example.remindbot.repo.RemindRepo;
import com.example.remindbot.repo.UserRepo;
import com.example.remindbot.service.RemindService;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@PropertySource(value = "classpath:scheduler.properties")
public class RemindServiceImpl implements RemindService {

    private final UserRepo userRepo;
    private final RemindRepo remindRepo;

    @Override
    public Reminder saveRemind(Reminder reminder) {
        int serverDifference = Integer.parseInt(userRepo.getTimezone(reminder.getChatId())
                .replace("GMT", ""));
        reminder.setCreatedTo(reminder.getCreatedTo().minusHours(serverDifference));
        if (reminder.getCreatedTo().toLocalDate().equals(LocalDate.now(ZoneId.of("GMT")))) {
            return remindRepo.save(reminder);
        }
        remindRepo.save(reminder);
        return null;
    }

    @Override
    @Scheduled(cron = "${cron.delete-old}", zone = "GMT")
    public void deleteOld() {
        remindRepo.deleteOld(LocalDate.now(ZoneId.of("GMT")));
    }

    @Override
    @Scheduled(cron = "${cron.get-actual}", zone = "GMT")
    public List<Reminder> getActual() {
        return remindRepo.actualReminders(LocalDate.now(ZoneId.of("GMT")));
    }

    @Override
    public void sendReminder() {


    }

}
