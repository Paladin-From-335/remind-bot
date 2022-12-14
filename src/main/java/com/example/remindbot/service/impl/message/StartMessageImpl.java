package com.example.remindbot.service.impl.message;

import static com.example.remindbot.model.constants.Response.GREETINGS;
import static com.example.remindbot.utils.KeyboardUtil.getKeyboardMarkup;
import static com.example.remindbot.utils.ResponseBuilder.buildResponse;

import com.example.remindbot.model.constants.State;
import com.example.remindbot.model.dto.ServiceWrapper;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class StartMessageImpl extends MessageServiceImpl {

    private final State key = State.START;

    @Override
    public BotApiMethod<?> handleMessage(ServiceWrapper wrapper) {
        return buildResponse(
                wrapper.getId(),
                GREETINGS.toString(),
                getKeyboardMarkup());
    }
}
