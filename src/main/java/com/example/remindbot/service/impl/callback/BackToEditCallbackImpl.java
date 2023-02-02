package com.example.remindbot.service.impl.callback;

import static com.example.remindbot.utils.ResponseBuilder.buildResponse;

import com.example.remindbot.config.DataWrapperConfig;
import com.example.remindbot.model.constants.State;
import com.example.remindbot.model.dto.ReminderDto;
import com.example.remindbot.service.CallbackService;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.springframework.stereotype.Component;

@Component("return_to_edit_query")
public class BackToEditCallbackImpl implements CallbackService {

    @Override
    public BotApiMethod<?> handleCallbackQuery(Long id, Integer msgId, DataWrapperConfig data) {
        data.getStates().saveStateCash(id, State.EDIT);
        ReminderDto reminder = data.getEvents().getEvent(id);
        return buildResponse(id, msgId,
                "Current reminder data:\n" + data.getMapper().dtoToResponse(reminder) + "\n\nWhat do you wanna edit?",
                data.getKeyboard().getEditRemMarkup());
    }
}