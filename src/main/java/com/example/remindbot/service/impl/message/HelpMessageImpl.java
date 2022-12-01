package com.example.remindbot.service.impl.message;

import static com.example.remindbot.model.constants.Response.HELP_INFO;
import static com.example.remindbot.utils.ResponseBuilder.buildResponse;

import com.example.remindbot.model.dto.ServiceWrapper;
import com.example.remindbot.service.MessageService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.springframework.stereotype.Component;

@Component
public class HelpMessageImpl implements MessageService {

    @Override
    public SendMessage handleMessage(ServiceWrapper wrapper) {
        return buildResponse(wrapper.getId(), HELP_INFO.toString());
    }
}
