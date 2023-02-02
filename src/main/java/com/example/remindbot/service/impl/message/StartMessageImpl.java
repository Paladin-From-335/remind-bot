package com.example.remindbot.service.impl.message;

import static com.example.remindbot.model.constants.Response.GREETINGS;
import static com.example.remindbot.utils.ResponseBuilder.buildResponse;

import com.example.remindbot.config.DataWrapperConfig;
import com.example.remindbot.model.constants.State;
import com.example.remindbot.service.MessageService;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component("START")
public class StartMessageImpl implements MessageService {

    @Override
    public BotApiMethod<?> handleMessage(Long id, String userMessage, DataWrapperConfig data) {
        return buildResponse(
                id,
                GREETINGS.toString(),
                data.getKeyboard().getMainMarkup());
    }
}
