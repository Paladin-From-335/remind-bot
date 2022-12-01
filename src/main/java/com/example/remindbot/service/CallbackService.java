package com.example.remindbot.service;

import com.example.remindbot.model.dto.CallbackWrapper;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.springframework.stereotype.Service;

@Service
public interface CallbackService {
    SendMessage handleCallbackQuery(CallbackWrapper wrapper);
}
