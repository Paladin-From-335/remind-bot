package com.example.remindbot.service.impl.message;

import static com.example.remindbot.model.constants.Response.CREATING_TEXT;
import static com.example.remindbot.utils.ResponseBuilder.buildResponse;

import com.example.remindbot.model.constants.State;
import com.example.remindbot.model.dto.ReminderDto;
import com.example.remindbot.model.dto.ServiceWrapper;
import com.example.remindbot.service.MessageService;
import com.example.remindbot.utils.cash.EventCash;
import com.example.remindbot.utils.cash.StateCash;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.springframework.stereotype.Component;

@Component
public class CreateMessageImpl implements MessageService {

    @Override
    public SendMessage handleMessage(ServiceWrapper wrapper) {
        Long id = wrapper.getId();
        StateCash.saveStateCash(id, State.REMIND_DESCRIPTION);
        EventCash.saveEventCash(id, new ReminderDto(id));
        return buildResponse(id, CREATING_TEXT.toString());
    }
}
