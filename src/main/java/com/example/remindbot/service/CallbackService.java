package com.example.remindbot.service;

import com.example.remindbot.model.dto.CallbackWrapper;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.springframework.stereotype.Service;

@Service
public interface CallbackService {

    BotApiMethod<?> handleCallbackQuery(CallbackWrapper wrapper);

}
