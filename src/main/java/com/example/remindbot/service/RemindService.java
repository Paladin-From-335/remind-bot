package com.example.remindbot.service;

import com.example.remindbot.model.entity.Reminder;
import java.util.List;

public interface RemindService {

    Reminder saveRemind(Reminder reminder);

    void deleteOld();

    List<Reminder> getActual();

    void sendReminder();
}
