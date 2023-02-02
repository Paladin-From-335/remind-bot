package com.example.remindbot.service.impl.callback;

import static com.example.remindbot.utils.ResponseBuilder.buildResponse;

import com.example.remindbot.config.DataWrapperConfig;
import com.example.remindbot.model.dto.ReminderDto;
import com.example.remindbot.service.CallbackService;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.springframework.stereotype.Component;

@Component("return_to_menu_query")
public class ReturnCallbackImpl implements CallbackService {

    @Override
    public BotApiMethod<?> handleCallbackQuery(Long id, Integer msgId, DataWrapperConfig data) {
        ReminderDto reminder = data.getEvents().getEvent(id);
        return buildResponse(id, msgId, data.getMapper().dtoToResponse(reminder),
                data.getKeyboard().getReminderMarkup());
    }
}
