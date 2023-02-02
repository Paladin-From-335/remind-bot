package com.example.remindbot.service.impl.message;

import static com.example.remindbot.model.constants.Response.CREATING_DATE;
import static com.example.remindbot.utils.ResponseBuilder.buildResponse;

import com.example.remindbot.config.DataWrapperConfig;
import com.example.remindbot.model.constants.State;
import com.example.remindbot.service.MessageService;
import com.example.remindbot.service.handler.cash.EventCash;
import com.example.remindbot.service.handler.cash.StateCash;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component("REMIND_DESCRIPTION")
public class TextMessageImpl implements MessageService {

    @Override
    public BotApiMethod<?> handleMessage(Long id, String userMessage, DataWrapperConfig data) {
        data.getStates().saveStateCash(id, State.REMIND_DATE);
        data.getEvents().getEvent(id).setRemindText(userMessage);
        return buildResponse(id, CREATING_DATE.toString());
    }
}
