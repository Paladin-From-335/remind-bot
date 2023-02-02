package com.example.remindbot.service.handler.cash;

import com.example.remindbot.model.entity.Reminder;
import java.util.Comparator;
import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ReminderCash {

    private final Deque<Reminder> todaySReminders = new ConcurrentLinkedDeque<>();

    public void saveTodaySReminder(Reminder event) {
        todaySReminders.add(event);
        sortReminders();
    }

    public void removeTodaySReminder(Reminder reminder) {
        todaySReminders.remove(reminder);
    }

    public Boolean isExist(Reminder reminder) {
        return todaySReminders.contains(reminder);
    }

    private void sortReminders() {
        todaySReminders.stream().sorted(Comparator.comparing(Reminder::getCreatedTo));
    }
}
