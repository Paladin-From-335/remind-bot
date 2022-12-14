package com.example.remindbot.service.impl.callback;

import static com.example.remindbot.utils.KeyboardUtil.getMessageMarkup;
import static com.example.remindbot.utils.ResponseBuilder.buildResponse;
import static com.example.remindbot.utils.cash.EventCash.isEventExist;

import com.example.remindbot.model.dto.CallbackWrapper;
import com.example.remindbot.service.CallbackService;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.springframework.stereotype.Component;

@Component("return_to_menu_query")
public class ReturnCallbackImpl implements CallbackService {

    @Override
    public BotApiMethod<?> handleCallbackQuery(CallbackWrapper wrapper) {
        if (isEventExist(wrapper.getId())) {
            return buildResponse(wrapper.getId(), getMessageMarkup());
        }
        return buildResponse(wrapper.getId());
    }
}
