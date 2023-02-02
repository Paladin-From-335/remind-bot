package com.example.remindbot.service.impl.message;

import static com.example.remindbot.model.constants.Response.CREATING_TEXT;
import static com.example.remindbot.utils.ResponseBuilder.buildResponse;

import com.example.remindbot.config.DataWrapperConfig;
import com.example.remindbot.model.constants.State;
import com.example.remindbot.model.dto.ReminderDto;
import com.example.remindbot.service.MessageService;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component("CREATE")
public class CreateMessageImpl implements MessageService {
//TODO if unsaved send reminder + unsaved
    @Override
    public BotApiMethod<?> handleMessage(Long id, String userMessage, DataWrapperConfig data) {
        data.getStates().saveStateCash(id, State.REMIND_DESCRIPTION);
        data.getEvents().saveEventCash(id, new ReminderDto(id));
        return buildResponse(id, CREATING_TEXT.toString());
    }
}
