package com.example.remindbot.service;

import com.example.remindbot.model.dto.ServiceWrapper;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.springframework.stereotype.Service;

@Service
public interface MessageService {

    BotApiMethod<?> handleMessage(ServiceWrapper wrapper);

}
