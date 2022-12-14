package com.example.remindbot.service.impl.message;

import static com.example.remindbot.model.constants.Response.ELAPSED_TIME;
import static com.example.remindbot.utils.DateTimeUtil.validateDateTime;
import static com.example.remindbot.utils.KeyboardUtil.getEditMarkup;
import static com.example.remindbot.utils.Mapper.dtoToResponse;
import static com.example.remindbot.utils.ResponseBuilder.buildResponse;

import com.example.remindbot.model.constants.State;
import com.example.remindbot.model.dto.ReminderDto;
import com.example.remindbot.model.dto.ServiceWrapper;
import com.example.remindbot.utils.cash.EventCash;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class EditTimeMessageImpl extends MessageServiceImpl {

    private final State key = State.EDIT_TIME;

    @Override
    public BotApiMethod<?> handleMessage(ServiceWrapper wrapper) {
        Long id = wrapper.getId();
        String reminderTime = wrapper.getUserMsg();
        String userTimezone = wrapper.getUserRepo().getUserTimezone(id);
        ReminderDto reminder = EventCash.getEvent(id);
        if (validateDateTime(reminder.getDateTo(), reminderTime, userTimezone)) {
            EventCash.getEvent(id).setDateTo(reminderTime);
            return buildResponse(
                    id,
                    dtoToResponse(reminder),
                    getEditMarkup()
            );
        }
        return buildResponse(id, ELAPSED_TIME.toString());
    }
}
