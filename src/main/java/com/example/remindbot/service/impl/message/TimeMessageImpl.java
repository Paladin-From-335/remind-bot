package com.example.remindbot.service.impl.message;

import static com.example.remindbot.model.constants.Response.ELAPSED_TIME;
import static com.example.remindbot.utils.DateTimeUtil.validateDateTime;
import static com.example.remindbot.utils.ResponseBuilder.buildResponse;

import com.example.remindbot.config.DataWrapperConfig;
import com.example.remindbot.model.dto.ReminderDto;
import com.example.remindbot.service.MessageService;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component("REMIND_TIME")
public class TimeMessageImpl implements MessageService {

    @Override
    public BotApiMethod<?> handleMessage(Long id, String reminderTime, DataWrapperConfig data) {
        String userTimezone = data.getUserRepo().getTimezone(id);
        ReminderDto reminder = data.getEvents().getEvent(id);
        if (validateDateTime(reminder.getDateTo(), reminderTime, userTimezone)) {
            data.getEvents().getEvent(id).setTimeTo(reminderTime);
            return buildResponse(
                    id,
                    data.getMapper().dtoToResponse(reminder),
                    data.getKeyboard().getReminderMarkup()
            );
        }
        return buildResponse(id, ELAPSED_TIME.toString());
    }
}
