package com.example.remindbot.service.impl.callback;

import static com.example.remindbot.utils.KeyboardUtil.getEditMarkup;
import static com.example.remindbot.utils.ResponseBuilder.buildResponse;

import com.example.remindbot.model.dto.CallbackWrapper;
import com.example.remindbot.service.CallbackService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.springframework.stereotype.Component;

@Component
public class EditCallbackImpl implements CallbackService {

    @Override
    public SendMessage handleCallbackQuery(CallbackWrapper wrapper) {
        return buildResponse(
                wrapper.getId(),
                "What do you want to change?",
                getEditMarkup()
        );
    }
}
