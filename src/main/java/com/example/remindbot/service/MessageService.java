package com.example.remindbot.service;

import com.example.remindbot.model.dto.ServiceWrapper;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface MessageService {

    SendMessage handleMessage(ServiceWrapper wrapper);

}
