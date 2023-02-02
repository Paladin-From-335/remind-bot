package com.example.remindbot.service.impl.message;

import static com.example.remindbot.utils.ResponseBuilder.buildResponse;

import com.example.remindbot.config.DataWrapperConfig;
import com.example.remindbot.model.constants.State;
import com.example.remindbot.service.MessageService;
import com.example.remindbot.utils.KeyboardUtil;
import com.example.remindbot.utils.Mapper;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Getter
@Component("EDIT_TEXT")
@RequiredArgsConstructor
public class EditTextMessageImpl implements MessageService {

    @Override
    public BotApiMethod<?> handleMessage(Long id, String userMessage, DataWrapperConfig data) {
        data.getEvents().getEvent(id).setRemindText(userMessage);
        return buildResponse(
                id,
                data.getMapper().dtoToResponse(data.getEvents().getEvent(id)),
                data.getKeyboard().getReminderMarkup()
        );
    }
}
