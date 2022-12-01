package com.example.remindbot.service.impl.message;

import static com.example.remindbot.model.constants.Response.GREETINGS;
import static com.example.remindbot.utils.KeyboardUtil.getKeyboardMarkup;
import static com.example.remindbot.utils.ResponseBuilder.buildResponse;

import com.example.remindbot.model.dto.ServiceWrapper;
import com.example.remindbot.service.MessageService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.springframework.stereotype.Component;

@Component
public class StartMessageImpl implements MessageService {

    @Override
    public SendMessage handleMessage(ServiceWrapper wrapper) {
        return buildResponse(
                wrapper.getId(),
                GREETINGS.toString(),
                getKeyboardMarkup());
    }
}
