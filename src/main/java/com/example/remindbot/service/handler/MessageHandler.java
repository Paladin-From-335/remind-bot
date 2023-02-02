package com.example.remindbot.service.handler;

import static com.example.remindbot.model.constants.Response.UNRECOGNIZED;
import static com.example.remindbot.utils.ResponseBuilder.buildResponse;

import com.example.remindbot.config.DataWrapperConfig;
import com.example.remindbot.config.ServiceConfig;
import com.example.remindbot.model.constants.State;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageHandler {

    private final ServiceConfig serviceConfig;
    private final DataWrapperConfig dataConfig;

    public BotApiMethod<?> handleMessage(Update update, State state) {
        Long id = update.getMessage().getChatId();
        String userMessage = update.getMessage().getText();
        if (state != State.START && dataConfig.getUserRepo().getTimezone(id) == null) {
            state = State.SET_TIMEZONE;
        }
        try {
            if (serviceConfig.messageServiceMap.containsKey(state.name())) {
                return serviceConfig.messageServiceMap
                        .get(state.name())
                        .handleMessage(id, userMessage, dataConfig);
            }
            return buildResponse(id, UNRECOGNIZED.toString(), dataConfig.getKeyboard().getMainMarkup());
        } catch (Exception e) {
            return buildResponse(id, e.getMessage());
        }
    }
}
