package com.example.remindbot.service;

import com.example.remindbot.config.DataWrapperConfig;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.springframework.stereotype.Service;

@Service
public interface CallbackService {

    BotApiMethod<?> handleCallbackQuery(Long id, Integer msgId, DataWrapperConfig data);

}
