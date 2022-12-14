package com.example.remindbot.service.impl.callback;

import static com.example.remindbot.utils.ResponseBuilder.buildResponse;
import static com.example.remindbot.utils.cash.EventCash.isEventExist;

import com.example.remindbot.model.dto.CallbackWrapper;
import com.example.remindbot.service.CallbackService;
import com.example.remindbot.utils.cash.EventCash;
import com.example.remindbot.utils.cash.StateCash;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.springframework.stereotype.Component;

@Component("delete_reminder_query")
public class DeleteCallbackImpl implements CallbackService {

    @Override
    public BotApiMethod<?> handleCallbackQuery(CallbackWrapper wrapper) {
        if (isEventExist(wrapper.getId())) {
            EventCash.deleteEventCash(wrapper.getId());
            StateCash.deleteState(wrapper.getId());
            return buildResponse(wrapper.getId(), "Removed");
        }
        return buildResponse(wrapper.getId());
    }
}
