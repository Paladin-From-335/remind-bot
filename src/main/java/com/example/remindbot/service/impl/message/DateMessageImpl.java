package com.example.remindbot.service.impl.message;

import static com.example.remindbot.model.constants.Response.CREATING_TIME;
import static com.example.remindbot.model.constants.Response.ELAPSED_DATE;
import static com.example.remindbot.utils.DateTimeUtil.validateDate;
import static com.example.remindbot.utils.ResponseBuilder.buildResponse;

import com.example.remindbot.config.DataWrapperConfig;
import com.example.remindbot.model.constants.State;
import com.example.remindbot.service.MessageService;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component("REMIND_DATE")
public class DateMessageImpl implements MessageService {

    @Override
    public BotApiMethod<?> handleMessage(Long id, String reminderDate, DataWrapperConfig data) {
        String userTimezone = data.getUserRepo().getTimezone(id);
        if (validateDate(reminderDate, userTimezone)) {
            data.getStates().saveStateCash(id, State.REMIND_TIME);
            data.getEvents().getEvent(id).setDateTo(reminderDate);
            return buildResponse(id, CREATING_TIME.toString());
        }
        return buildResponse(id, ELAPSED_DATE.toString());
    }
}
