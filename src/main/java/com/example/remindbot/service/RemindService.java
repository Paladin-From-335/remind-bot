package com.example.remindbot.service;

import com.example.remindbot.model.Reminder;
import com.example.remindbot.repo.RemindRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RemindService {

    private final RemindRepo remindRepo;

    public void saveRemind(Reminder reminder) {
        remindRepo.save(reminder);
    }


}
