package com.example.remindbot.service.impl.message;

import static com.example.remindbot.utils.KeyboardUtil.getEditMarkup;
import static com.example.remindbot.utils.Mapper.dtoToResponse;
import static com.example.remindbot.utils.ResponseBuilder.buildResponse;

import com.example.remindbot.model.constants.State;
import com.example.remindbot.model.dto.ServiceWrapper;
import com.example.remindbot.utils.cash.EventCash;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class EditTextMessageImpl extends MessageServiceImpl {

    private final State key = State.EDIT_TEXT;

    @Override
    public BotApiMethod<?> handleMessage(ServiceWrapper wrapper) {
        Long id = wrapper.getId();
        String reminderText = wrapper.getUserMsg();
        EventCash.getEvent(id).setRemindText(reminderText);
        return buildResponse(
                id,
                dtoToResponse(EventCash.getEvent(id)),
                getEditMarkup()
        );
    }
}
