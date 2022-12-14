package com.example.remindbot.service.impl.message;

import static com.example.remindbot.model.constants.Response.HELP_INFO;
import static com.example.remindbot.utils.ResponseBuilder.buildResponse;

import com.example.remindbot.model.constants.State;
import com.example.remindbot.model.dto.ServiceWrapper;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class HelpMessageImpl extends MessageServiceImpl {

    private final State key = State.HELP;

    @Override
    public BotApiMethod<?> handleMessage(ServiceWrapper wrapper) {
        return buildResponse(wrapper.getId(), HELP_INFO.toString());
    }
}
