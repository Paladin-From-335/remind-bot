package com.example.remindbot.service;

import com.example.remindbot.model.Remind;
import com.example.remindbot.repo.RemindRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RemindService {

    private final RemindRepo remindRepo;

    public void saveRemind(Remind remind) {
        remindRepo.save(remind);
    }


}
