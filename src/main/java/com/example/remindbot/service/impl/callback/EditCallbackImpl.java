package com.example.remindbot.service.impl.callback;

import static com.example.remindbot.utils.KeyboardUtil.getEditMarkup;
import static com.example.remindbot.utils.ResponseBuilder.buildResponse;
import static com.example.remindbot.utils.cash.EventCash.isEventExist;

import com.example.remindbot.model.dto.CallbackWrapper;
import com.example.remindbot.service.CallbackService;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.springframework.stereotype.Component;

@Component("edit_reminder_query")
public class EditCallbackImpl implements CallbackService {

    @Override
    public BotApiMethod<?> handleCallbackQuery(CallbackWrapper wrapper) {
        if (isEventExist(wrapper.getId())) {
            return buildResponse(
                    wrapper.getId(),
                    "What do you wanna edit?",
                    getEditMarkup()
            );
        }
        return buildResponse(wrapper.getId());
    }
}
