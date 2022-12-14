package com.example.remindbot.service.impl.message;

import static com.example.remindbot.model.constants.Response.CREATING_TEXT;
import static com.example.remindbot.utils.ResponseBuilder.buildResponse;

import com.example.remindbot.model.constants.State;
import com.example.remindbot.model.dto.ReminderDto;
import com.example.remindbot.model.dto.ServiceWrapper;
import com.example.remindbot.utils.cash.EventCash;
import com.example.remindbot.utils.cash.StateCash;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import lombok.Getter;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.stereotype.Component;

@Getter
@Component
public class CreateMessageImpl extends MessageServiceImpl {

    private final State key = State.CREATE;

    @Override
    public BotApiMethod<?> handleMessage(ServiceWrapper wrapper) {
        Long id = wrapper.getId();
        StateCash.saveStateCash(id, State.REMIND_DESCRIPTION);
        EventCash.saveEventCash(id, new ReminderDto(id));
        return buildResponse(id, CREATING_TEXT.toString());
    }
}
