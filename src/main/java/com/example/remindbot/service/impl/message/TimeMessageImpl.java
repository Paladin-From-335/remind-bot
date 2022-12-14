package com.example.remindbot.service.impl.message;

import static com.example.remindbot.model.constants.Response.ELAPSED_TIME;
import static com.example.remindbot.utils.DateTimeUtil.validateDateTime;
import static com.example.remindbot.utils.KeyboardUtil.getMessageMarkup;
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
public class TimeMessageImpl extends MessageServiceImpl {

    private final State key = State.REMIND_TIME;

    @Override
    public BotApiMethod<?> handleMessage(ServiceWrapper wrapper) {
        Long id = wrapper.getId();
        String reminderTime = wrapper.getUserMsg();
        String userTimezone = wrapper.getUserRepo().getUserTimezone(id);
        ReminderDto reminder = EventCash.getEvent(id);
        if (validateDateTime(reminder.getDateTo(), reminderTime, userTimezone)) {
            EventCash.getEvent(id).setTimeTo(reminderTime);
            return buildResponse(
                    id,
                    dtoToResponse(reminder),
                    getMessageMarkup()
            );
        }
        return buildResponse(id, ELAPSED_TIME.toString());
    }
}
