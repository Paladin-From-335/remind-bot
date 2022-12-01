package com.example.remindbot.service.impl.message;

import static com.example.remindbot.utils.Mapper.dtoToResponse;
import static com.example.remindbot.utils.ResponseBuilder.buildResponse;

import com.example.remindbot.model.dto.ServiceWrapper;
import com.example.remindbot.service.MessageService;
import com.example.remindbot.utils.cash.EventCash;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.springframework.stereotype.Component;

@Component
public class EditTextMessageImpl implements MessageService {

    @Override
    public SendMessage handleMessage(ServiceWrapper wrapper) {
        Long id = wrapper.getId();
        String reminderText = wrapper.getUserMsg();
        EventCash.getEvent(id).setRemindText(reminderText);
        return buildResponse(
                id,
                dtoToResponse(EventCash.getEvent(id))
        );
    }
}
