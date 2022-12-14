package com.example.remindbot.service.impl.callback;

import static com.example.remindbot.utils.ResponseBuilder.buildResponse;
import static com.example.remindbot.utils.cash.EventCash.isEventExist;

import com.example.remindbot.model.constants.State;
import com.example.remindbot.model.dto.CallbackWrapper;
import com.example.remindbot.service.CallbackService;
import com.example.remindbot.utils.cash.StateCash;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.springframework.stereotype.Component;

@Component("edit_time_query")
public class EditTimeCallbackImpl implements CallbackService {

    @Override
    public BotApiMethod<?> handleCallbackQuery(CallbackWrapper wrapper) {
        if (isEventExist(wrapper.getId())) {
            StateCash.saveStateCash(wrapper.getId(), State.EDIT_TIME);
            return buildResponse(wrapper.getId(), "Write reminder time");
        }
        return buildResponse(wrapper.getId());
    }
}