package com.example.remindbot.service.impl.message;

import static com.example.remindbot.model.constants.Response.CREATING_TIME;
import static com.example.remindbot.model.constants.Response.ELAPSED_DATE;
import static com.example.remindbot.utils.DateTimeUtil.validateDate;
import static com.example.remindbot.utils.ResponseBuilder.buildResponse;

import com.example.remindbot.model.constants.State;
import com.example.remindbot.model.dto.ServiceWrapper;
import com.example.remindbot.service.MessageService;
import com.example.remindbot.utils.cash.EventCash;
import com.example.remindbot.utils.cash.StateCash;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.springframework.stereotype.Component;

@Component
public class DateMessageImpl implements MessageService {

    @Override
    public SendMessage handleMessage(ServiceWrapper wrapper) {
        Long id = wrapper.getId();
        String userTimezone = wrapper.getUserRepo().getUserTimezone(id);
        String reminderDate = wrapper.getUserMsg();
        if (validateDate(reminderDate, userTimezone)) {
            StateCash.saveStateCash(id, State.REMIND_TIME);
            EventCash.getEvent(id).setDateTo(reminderDate);
            return buildResponse(id, CREATING_TIME.toString());
        }
        return buildResponse(id, ELAPSED_DATE.toString());
    }
}
