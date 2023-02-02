package com.example.remindbot.config;

import com.example.remindbot.repo.RemindRepo;
import com.example.remindbot.repo.UserRepo;
import com.example.remindbot.service.RemindService;
import com.example.remindbot.service.handler.cash.EventCash;
import com.example.remindbot.service.handler.cash.MessageIdCash;
import com.example.remindbot.service.handler.cash.ReminderCash;
import com.example.remindbot.service.handler.cash.StateCash;
import com.example.remindbot.utils.KeyboardUtil;
import com.example.remindbot.utils.Mapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@RequiredArgsConstructor
public class DataWrapperConfig {
    private final UserRepo userRepo;
    private final RemindRepo remindRepo;
    private final KeyboardUtil keyboard;
    private final Mapper mapper;
    private final EventCash events;
    private final StateCash states;
    private final ReminderCash reminders;
    private final MessageIdCash msgIds;
    private final RemindService remService;
}
