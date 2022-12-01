package com.example.remindbot.service.impl.message;

import static com.example.remindbot.model.constants.Response.CREATING_DATE;
import static com.example.remindbot.utils.ResponseBuilder.buildResponse;

import com.example.remindbot.model.constants.State;
import com.example.remindbot.model.dto.ServiceWrapper;
import com.example.remindbot.service.MessageService;
import com.example.remindbot.utils.cash.EventCash;
import com.example.remindbot.utils.cash.StateCash;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.springframework.stereotype.Component;

@Component
public class TextMessageImpl implements MessageService {

    @Override
    public SendMessage handleMessage(ServiceWrapper wrapper) {
        Long id = wrapper.getId();
        String userMessage = wrapper.getUserMsg();
        StateCash.saveStateCash(id, State.REMIND_DATE);
        EventCash.getEvent(id).setRemindText(userMessage);
        return buildResponse(id, CREATING_DATE.toString());
    }
}
